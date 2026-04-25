<template>
    <div class="p-6 space-y-6">

        <!-- ── Page Header ──────────────────────────────────────────────────── -->
        <div class="flex items-center justify-between">
            <div>
                <h1 class="text-xl font-bold text-gray-900">User Management</h1>
                <p class="text-sm text-gray-400 mt-0.5">Identity · Trust · Behaviour · Geographic reach</p>
            </div>
            <div class="flex items-center gap-2">
                <button
                    @click="exportCSV"
                    class="flex items-center gap-2 border border-gray-200 text-gray-600 hover:bg-gray-50 text-sm font-medium px-4 py-2.5 rounded-xl transition-colors duration-150"
                >
                    <Download class="w-4 h-4" />
                    Export
                </button>
                <button
                    @click="openInviteModal"
                    class="flex items-center gap-2 border border-gray-200 text-gray-600 hover:bg-gray-50 text-sm font-medium px-4 py-2.5 rounded-xl transition-colors duration-150"
                >
                    <UserPlus class="w-4 h-4" />
                    Invite User
                </button>
                <button
                    @click="filterStatus = 'Pending'"
                    class="flex items-center gap-2 bg-amber-500 hover:bg-amber-600 text-white text-sm font-medium px-4 py-2.5 rounded-xl transition-colors duration-150 shadow-sm relative"
                >
                    <ShieldAlert class="w-4 h-4" />
                    KYC Queue
                    <span v-if="pendingKycCount > 0" class="absolute -top-1.5 -right-1.5 w-5 h-5 rounded-full bg-red-500 text-white text-[10px] font-bold flex items-center justify-center">
                        {{ pendingKycCount }}
                    </span>
                </button>
            </div>
        </div>

        <!-- ── Stat Cards ────────────────────────────────────────────────────── -->
        <section class="grid grid-cols-2 md:grid-cols-6 gap-4">
            <AdminStatsCard title="Total Users"    :value="String(users.length)"               :percent="6"   :icon="Users" />
            <AdminStatsCard title="Farmers"        :value="String(countByRole('Farmer'))"       :percent="11"  :icon="Tractor" />
            <AdminStatsCard title="Buyers"         :value="String(countByRole('Buyer'))"        :percent="4"   :icon="ShoppingBag" />
            <AdminStatsCard title="KYC Pending"    :value="String(pendingKycCount)"             :percent="0"   :icon="FileSearch" />
            <AdminStatsCard title="Suspended"      :value="String(countByStatus('Suspended'))"  :percent="-2"  :icon="ShieldX" />
            <AdminStatsCard title="Avg Trust"      :value="avgTrustScore + '/100'"              :percent="2"   :icon="BadgeCheck" />
        </section>

        <!-- ── Top Row: KYC Queue + Activity Feed ───────────────────────────── -->
        <section class="grid grid-cols-5 gap-6">

            <!-- KYC Verification Queue — col-span-3 -->
            <div class="col-span-3 bg-white rounded-2xl shadow-sm border border-gray-100">
                <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                    <div class="flex items-center gap-2">
                        <div class="w-2 h-2 rounded-full bg-amber-400 animate-pulse"></div>
                        <h3 class="text-sm font-semibold text-gray-900">KYC Verification Queue</h3>
                    </div>
                    <div class="flex items-center gap-2">
                        <span class="text-xs font-medium px-2.5 py-1 rounded-full bg-amber-100 text-amber-700">
                            {{ pendingKycCount }} awaiting
                        </span>
                        <button
                            @click="bulkApproveKyc"
                            class="text-xs font-medium px-3 py-1.5 rounded-lg bg-green-600 hover:bg-green-700 text-white transition flex items-center gap-1"
                        >
                            <CheckCheck class="w-3 h-3" /> Bulk Approve
                        </button>
                    </div>
                </div>

                <div v-if="pendingKycUsers.length === 0" class="flex flex-col items-center gap-3 py-12">
                    <div class="w-14 h-14 rounded-2xl bg-green-50 flex items-center justify-center">
                        <ShieldCheck class="w-6 h-6 text-green-500" />
                    </div>
                    <p class="text-sm font-medium text-gray-500">All identities verified!</p>
                    <p class="text-xs text-gray-400">No users waiting for KYC review</p>
                </div>

                <div v-else class="divide-y divide-gray-50">
                    <div
                        v-for="u in pendingKycUsers.slice(0, 5)" :key="u.id"
                        class="flex items-center gap-4 px-5 py-3.5 hover:bg-gray-50/60 transition group"
                    >
                        <!-- Avatar -->
                        <div class="w-9 h-9 rounded-full flex items-center justify-center text-xs font-bold flex-shrink-0"
                            :class="roleAvatarClass(u.role)">
                            {{ initials(u.name) }}
                        </div>

                        <!-- Info -->
                        <div class="flex-1 min-w-0">
                            <div class="flex items-center gap-2">
                                <p class="text-sm font-semibold text-gray-800 truncate">{{ u.name }}</p>
                                <span class="text-xs font-medium px-2 py-0.5 rounded-full flex-shrink-0" :class="roleClass(u.role)">{{ u.role }}</span>
                            </div>
                            <div class="flex items-center gap-2 mt-0.5">
                                <p class="text-xs text-gray-400 font-mono">{{ u.email }}</p>
                                <span class="text-gray-200">·</span>
                                <p class="text-xs text-gray-400">{{ u.submittedKycAt }}</p>
                            </div>
                        </div>

                        <!-- Docs badge -->
                        <span class="text-xs font-medium px-2 py-1 rounded-lg flex-shrink-0"
                            :class="u.docsComplete ? 'bg-blue-50 text-blue-600' : 'bg-red-50 text-red-500'">
                            {{ u.docsComplete ? 'Docs OK' : 'Incomplete' }}
                        </span>

                        <!-- Action Buttons -->
                        <div class="flex items-center gap-1.5 opacity-0 group-hover:opacity-100 transition-opacity">
                            <button
                                @click="openUserModal(u)"
                                class="text-xs font-medium px-3 py-1.5 rounded-lg border border-gray-200 text-gray-500 hover:bg-gray-50 transition flex items-center gap-1"
                            ><Eye class="w-3 h-3" /> View</button>
                            <button
                                @click="openRejectKycModal(u)"
                                class="text-xs font-medium px-3 py-1.5 rounded-lg border border-red-200 text-red-500 hover:bg-red-50 transition"
                            >Reject</button>
                            <button
                                @click="verifyUser(u.id)"
                                class="text-xs font-medium px-3 py-1.5 rounded-lg bg-green-600 hover:bg-green-700 text-white transition"
                            >Verify</button>
                        </div>
                    </div>
                </div>

                <div v-if="pendingKycUsers.length > 5" class="px-5 py-3 border-t border-gray-100">
                    <button @click="filterStatus = 'Pending'" class="text-xs text-green-600 hover:underline font-medium">
                        View all {{ pendingKycUsers.length }} pending KYC →
                    </button>
                </div>
            </div>

            <!-- User Activity Feed — col-span-2 -->
            <div class="col-span-2 bg-white rounded-2xl shadow-sm border border-gray-100 p-5">
                <div class="flex items-center justify-between mb-4">
                    <h3 class="text-sm font-semibold text-gray-900">User Activity</h3>
                    <div class="flex items-center gap-1.5">
                        <div class="w-1.5 h-1.5 rounded-full bg-green-400 animate-pulse"></div>
                        <span class="text-xs text-gray-400">live</span>
                    </div>
                </div>
                <div class="space-y-0">
                    <div v-for="act in userActivity" :key="act.id" class="flex gap-3 py-3 border-b border-gray-50 last:border-0">
                        <div class="w-2 h-2 rounded-full mt-1.5 flex-shrink-0" :class="act.dotColor"></div>
                        <div class="flex-1 min-w-0">
                            <p class="text-xs text-gray-700 leading-snug">{{ act.text }}</p>
                            <p class="text-[11px] text-gray-400 mt-0.5 font-mono">{{ act.time }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- ── Trust Score + Geography + Churn ───────────────────────────────── -->
        <section class="grid grid-cols-3 gap-6">

            <!-- Trust Score Leaderboard -->
            <div class="bg-white rounded-2xl shadow-sm border border-gray-100">
                <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                    <div class="flex items-center gap-2">
                        <BadgeCheck class="w-4 h-4 text-purple-500" />
                        <h3 class="text-sm font-semibold text-gray-900">Trust Score Index</h3>
                    </div>
                    <span class="text-xs font-mono text-gray-400">composite</span>
                </div>
                <div class="divide-y divide-gray-50">
                    <div
                        v-for="(u, i) in trustLeaderboard" :key="u.id"
                        class="flex items-center gap-3 px-5 py-3 hover:bg-gray-50/60 transition cursor-pointer group"
                        @click="openUserModal(u)"
                    >
                        <span class="text-xs font-mono text-gray-300 w-4 flex-shrink-0">#{{ i + 1 }}</span>
                        <div class="w-7 h-7 rounded-full flex items-center justify-center text-[10px] font-bold flex-shrink-0"
                            :class="roleAvatarClass(u.role)">
                            {{ initials(u.name) }}
                        </div>
                        <div class="flex-1 min-w-0">
                            <p class="text-xs font-semibold text-gray-800 truncate">{{ u.name }}</p>
                            <p class="text-[11px] text-gray-400">{{ u.orders }} orders · {{ u.rating }}★</p>
                        </div>
                        <div class="flex flex-col items-end gap-1 flex-shrink-0">
                            <span class="text-xs font-bold font-mono" :class="trustScoreColor(u.trustScore)">{{ u.trustScore }}</span>
                            <div class="w-14 h-1 bg-gray-100 rounded-full overflow-hidden">
                                <div class="h-full rounded-full transition-all duration-700"
                                    :style="{ width: u.trustScore + '%', background: trustScoreBg(u.trustScore) }"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="px-5 py-3 border-t border-gray-100">
                    <p class="text-[11px] text-gray-300 font-mono">score = orders×0.3 + rating×0.3 + kyc×0.2 − disputes×0.2</p>
                </div>
            </div>

            <!-- Geographic Density -->
            <div class="bg-white rounded-2xl shadow-sm border border-gray-100">
                <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                    <div class="flex items-center gap-2">
                        <MapPin class="w-4 h-4 text-teal-500" />
                        <h3 class="text-sm font-semibold text-gray-900">Geographic Density</h3>
                    </div>
                    <span class="text-xs font-mono text-gray-400">by province</span>
                </div>
                <div class="px-5 py-4 space-y-3">
                    <div v-for="geo in geoData" :key="geo.province" class="space-y-1">
                        <div class="flex items-center justify-between">
                            <span class="text-xs font-medium text-gray-700">{{ geo.province }}</span>
                            <div class="flex items-center gap-2">
                                <span class="text-xs text-gray-400 font-mono">{{ geo.count }}</span>
                                <span class="text-[11px] text-gray-300 font-mono">{{ geo.pct }}%</span>
                            </div>
                        </div>
                        <div class="w-full bg-gray-100 rounded-full h-1.5">
                            <div class="h-1.5 rounded-full transition-all duration-700"
                                :style="{ width: geo.pct + '%', background: '#0891b2' }"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Churn Risk Radar -->
            <div class="bg-white rounded-2xl shadow-sm border border-gray-100">
                <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                    <div class="flex items-center gap-2">
                        <TrendingDown class="w-4 h-4 text-red-500" />
                        <h3 class="text-sm font-semibold text-gray-900">Churn Risk Radar</h3>
                    </div>
                    <span class="text-xs font-mono text-gray-400">30d+ inactive</span>
                </div>

                <div class="grid grid-cols-3 gap-px bg-gray-100 border-b border-gray-100">
                    <div class="bg-white px-4 py-3 text-center">
                        <p class="text-lg font-bold text-red-500 font-mono">{{ churnCounts.high }}</p>
                        <p class="text-[10px] text-gray-400 font-mono mt-0.5">HIGH</p>
                    </div>
                    <div class="bg-white px-4 py-3 text-center">
                        <p class="text-lg font-bold text-amber-500 font-mono">{{ churnCounts.med }}</p>
                        <p class="text-[10px] text-gray-400 font-mono mt-0.5">MEDIUM</p>
                    </div>
                    <div class="bg-white px-4 py-3 text-center">
                        <p class="text-lg font-bold text-green-500 font-mono">{{ churnCounts.low }}</p>
                        <p class="text-[10px] text-gray-400 font-mono mt-0.5">LOW</p>
                    </div>
                </div>

                <div class="divide-y divide-gray-50">
                    <div v-for="u in churnRiskUsers.slice(0, 3)" :key="u.id"
                        class="flex items-center gap-3 px-5 py-2.5 hover:bg-gray-50/60 transition group">
                        <div class="w-7 h-7 rounded-full flex items-center justify-center text-[10px] font-bold flex-shrink-0"
                            :class="roleAvatarClass(u.role)">
                            {{ initials(u.name) }}
                        </div>
                        <div class="flex-1 min-w-0">
                            <p class="text-xs font-semibold text-gray-800">{{ u.name }}</p>
                            <p class="text-[11px] text-gray-400 font-mono">{{ u.daysSince }}d inactive · {{ u.role }}</p>
                        </div>
                        <span class="text-[10px] font-bold px-2 py-0.5 rounded-full"
                            :class="churnRiskClass(u.churnRisk)">{{ u.churnRisk }}</span>
                        <button @click="nudgeUser(u.id)"
                            class="opacity-0 group-hover:opacity-100 text-[10px] font-medium px-2 py-1 rounded-lg border border-blue-200 text-blue-600 hover:bg-blue-50 transition">
                            Nudge
                        </button>
                    </div>
                </div>
                <div class="px-5 py-3 border-t border-gray-100">
                    <button @click="sendReengagement" class="text-xs text-blue-600 hover:underline font-medium flex items-center gap-1">
                        <Mail class="w-3 h-3" /> Draft re-engagement campaign →
                    </button>
                </div>
            </div>
        </section>

        <!-- ── Role Change Audit Log + Growth Chart ───────────────────────────── -->
        <section class="grid grid-cols-2 gap-6">

            <!-- Growth Trend -->
            <div class="border border-gray-100 rounded-2xl p-5 shadow-sm bg-white">
                <div class="flex items-start justify-between gap-4 mb-5">
                    <h2 class="text-sm font-semibold text-gray-900">
                        User Growth
                        <span class="text-gray-400 font-normal">(Last 7 Days)</span>
                    </h2>
                    <div class="flex items-center gap-3 text-xs text-gray-400">
                        <span class="flex items-center gap-1"><span class="w-2 h-2 rounded-full bg-green-500 inline-block"></span> Farmers</span>
                        <span class="flex items-center gap-1"><span class="w-2 h-2 rounded-full bg-blue-400 inline-block"></span> Buyers</span>
                    </div>
                </div>
                <div class="relative h-40">
                    <svg class="w-full h-full overflow-visible" :viewBox="`0 0 ${TW} ${TH}`" preserveAspectRatio="none">
                        <defs>
                            <linearGradient id="farmerGrad" x1="0" y1="0" x2="0" y2="1">
                                <stop offset="0%"   stop-color="#22c55e" stop-opacity="0.18" />
                                <stop offset="100%" stop-color="#22c55e" stop-opacity="0.01" />
                            </linearGradient>
                            <linearGradient id="buyerGrad" x1="0" y1="0" x2="0" y2="1">
                                <stop offset="0%"   stop-color="#60a5fa" stop-opacity="0.14" />
                                <stop offset="100%" stop-color="#60a5fa" stop-opacity="0.01" />
                            </linearGradient>
                        </defs>
                        <line v-for="t in [0,5,10,15,20]" :key="'g'+t" x1="0" :y1="trendYs(t)" :x2="TW" :y2="trendYs(t)" stroke="#f3f4f6" stroke-width="1" />
                        <path :d="trendAreaPath('farmers')" fill="url(#farmerGrad)" />
                        <path :d="trendLinePath('farmers')" fill="none" stroke="#16a34a" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                        <path :d="trendAreaPath('buyers')" fill="url(#buyerGrad)" />
                        <path :d="trendLinePath('buyers')" fill="none" stroke="#60a5fa" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" stroke-dasharray="5 3" />
                    </svg>
                </div>
                <div class="flex justify-between mt-2">
                    <span v-for="d in trendDays" :key="d" class="text-[10px] text-gray-300 font-medium">{{ d }}</span>
                </div>
            </div>

            <!-- Role Change + Audit Log -->
            <div class="bg-white rounded-2xl shadow-sm border border-gray-100">
                <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                    <div class="flex items-center gap-2">
                        <ArrowLeftRight class="w-4 h-4 text-purple-500" />
                        <h3 class="text-sm font-semibold text-gray-900">Role Change Audit Log</h3>
                    </div>
                    <button @click="exportAuditLog" class="text-xs text-gray-400 hover:text-gray-600 flex items-center gap-1 font-medium">
                        <Download class="w-3 h-3" /> Export
                    </button>
                </div>
                <!-- Column headers -->
                <div class="grid grid-cols-5 gap-2 px-5 py-2 bg-gray-50 border-b border-gray-100 text-[10px] font-semibold text-gray-400 uppercase tracking-wide">
                    <span class="col-span-1">Time</span>
                    <span class="col-span-1">User</span>
                    <span class="col-span-1">From</span>
                    <span class="col-span-1">To</span>
                    <span class="col-span-1">By</span>
                </div>
                <div class="divide-y divide-gray-50">
                    <div v-for="log in auditLog" :key="log.id"
                        class="grid grid-cols-5 gap-2 items-center px-5 py-2.5 hover:bg-gray-50/60 transition text-xs">
                        <span class="text-gray-400 font-mono text-[11px]">{{ log.time }}</span>
                        <span class="font-semibold text-gray-800 truncate">{{ log.user }}</span>
                        <span class="font-medium px-1.5 py-0.5 rounded text-[10px] w-fit" :class="roleClass(log.from)">{{ log.from }}</span>
                        <span class="font-medium px-1.5 py-0.5 rounded text-[10px] w-fit" :class="roleClass(log.to)">{{ log.to }}</span>
                        <span class="text-gray-400 font-mono text-[10px]">{{ log.by }}</span>
                    </div>
                </div>
            </div>
        </section>

        <!-- ── Flagged Users Alert Band ───────────────────────────────────────── -->
        <section v-if="flaggedUsers.length > 0" class="bg-red-50 border border-red-100 rounded-2xl p-4">
            <div class="flex items-center justify-between mb-3">
                <div class="flex items-center gap-2">
                    <ShieldAlert class="w-4 h-4 text-red-500" />
                    <h3 class="text-sm font-semibold text-red-700">Flagged Users Requiring Attention</h3>
                </div>
                <span class="text-xs font-medium px-2.5 py-1 rounded-full bg-red-100 text-red-600">{{ flaggedUsers.length }} flagged</span>
            </div>
            <div class="grid grid-cols-4 gap-3">
                <div v-for="fu in flaggedUsers" :key="fu.id"
                    class="flex items-center gap-3 p-3 rounded-xl bg-white border border-red-100">
                    <div class="w-9 h-9 rounded-full flex items-center justify-center text-xs font-bold flex-shrink-0"
                        :class="roleAvatarClass(fu.role)">
                        {{ initials(fu.name) }}
                    </div>
                    <div class="flex-1 min-w-0">
                        <p class="text-xs font-semibold text-gray-800 truncate">{{ fu.name }}</p>
                        <p class="text-[11px] text-red-500 font-medium">{{ fu.flagReason }}</p>
                    </div>
                    <div class="flex flex-col gap-1">
                        <button @click="resolveFlag(fu.id)" class="text-[10px] font-medium px-2 py-1 rounded-lg bg-gray-900 text-white hover:bg-gray-700 transition">Resolve</button>
                        <button @click="banUser(fu.id)" class="text-[10px] font-medium px-2 py-1 rounded-lg border border-red-200 text-red-500 hover:bg-red-50 transition">Ban</button>
                    </div>
                </div>
            </div>
        </section>

        <!-- ── Full User Table ────────────────────────────────────────────────── -->
        <section class="bg-white rounded-2xl shadow-sm border border-gray-100">
            <div class="flex items-center gap-3 px-5 py-4 border-b border-gray-100 flex-wrap">
                <div class="relative flex-1 min-w-[180px]">
                    <Search class="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-300" />
                    <input v-model="searchQuery" type="text" placeholder="Search users or email…"
                        class="w-full pl-9 pr-4 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 transition" />
                </div>
                <div class="flex items-center gap-0.5 bg-gray-100 rounded-xl p-1">
                    <button v-for="s in statusTabs" :key="s" @click="filterStatus = s"
                        :class="['text-xs font-medium px-3 py-1.5 rounded-lg transition-all duration-150',
                            filterStatus === s ? 'bg-white text-gray-900 shadow-sm' : 'text-gray-400 hover:text-gray-600']">
                        {{ s }}
                    </button>
                </div>
                <select v-model="filterRole"
                    class="text-sm border border-gray-200 rounded-xl px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 bg-white text-gray-600">
                    <option value="">All Roles</option>
                    <option v-for="r in roles" :key="r" :value="r">{{ r }}</option>
                </select>
                <select v-model="filterTrust"
                    class="text-sm border border-gray-200 rounded-xl px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 bg-white text-gray-600">
                    <option value="">Any Trust</option>
                    <option value="high">High (80+)</option>
                    <option value="mid">Medium (50–79)</option>
                    <option value="low">Low (&lt;50)</option>
                </select>
            </div>

            <div class="overflow-x-auto">
                <table class="w-full text-sm">
                    <thead>
                        <tr class="text-left text-xs text-gray-400 border-b border-gray-100">
                            <th class="px-5 py-3 font-medium">User</th>
                            <th class="px-4 py-3 font-medium">Role</th>
                            <th class="px-4 py-3 font-medium">Province</th>
                            <th class="px-4 py-3 font-medium">Trust Score</th>
                            <th class="px-4 py-3 font-medium">Status</th>
                            <th class="px-4 py-3 font-medium">Joined</th>
                            <th class="px-4 py-3 font-medium text-right">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-if="filteredUsers.length === 0">
                            <td colspan="7" class="px-5 py-14 text-center">
                                <div class="flex flex-col items-center gap-3">
                                    <div class="w-14 h-14 rounded-2xl bg-gray-50 flex items-center justify-center">
                                        <UserX class="w-6 h-6 text-gray-300" />
                                    </div>
                                    <p class="text-sm font-medium text-gray-500">No users found</p>
                                    <p class="text-xs text-gray-400">Try adjusting your filters</p>
                                </div>
                            </td>
                        </tr>

                        <tr v-for="user in filteredUsers" :key="user.id"
                            class="border-b border-gray-50 hover:bg-gray-50/60 transition group"
                            :class="{
                                'opacity-40 pointer-events-none': processingId === user.id,
                                'bg-amber-50/40': user.status === 'Pending',
                                'bg-red-50/30':   user.status === 'Banned',
                            }">
                            <!-- User -->
                            <td class="px-5 py-3.5">
                                <div class="flex items-center gap-3">
                                    <div class="relative w-9 h-9 rounded-full flex items-center justify-center text-xs font-bold flex-shrink-0"
                                        :class="roleAvatarClass(user.role)">
                                        {{ initials(user.name) }}
                                        <div v-if="user.flagged" class="absolute -top-0.5 -right-0.5 w-3 h-3 bg-red-500 rounded-full border-2 border-white"></div>
                                    </div>
                                    <div>
                                        <p class="font-semibold text-gray-800 text-sm">{{ user.name }}</p>
                                        <p class="text-[11px] text-gray-400 font-mono">{{ user.email }}</p>
                                    </div>
                                </div>
                            </td>
                            <td class="px-4 py-3.5">
                                <span class="text-xs font-medium px-2 py-1 rounded-full" :class="roleClass(user.role)">{{ user.role }}</span>
                            </td>
                            <td class="px-4 py-3.5 text-xs text-gray-600">{{ user.province }}</td>
                            <!-- Trust Score Ring -->
                            <td class="px-4 py-3.5">
                                <div class="flex items-center gap-2">
                                    <div class="w-7 h-7 flex-shrink-0">
                                        <svg viewBox="0 0 28 28" class="w-7 h-7 -rotate-90">
                                            <circle cx="14" cy="14" r="11" fill="none" stroke="#f3f4f6" stroke-width="3"/>
                                            <circle cx="14" cy="14" r="11" fill="none"
                                                :stroke="trustScoreBg(user.trustScore)"
                                                stroke-width="3"
                                                stroke-linecap="round"
                                                :stroke-dasharray="`${(user.trustScore/100)*69.1} 69.1`"/>
                                        </svg>
                                    </div>
                                    <span class="text-xs font-bold font-mono" :class="trustScoreColor(user.trustScore)">{{ user.trustScore }}</span>
                                </div>
                            </td>
                            <td class="px-4 py-3.5">
                                <span class="text-xs font-medium px-2.5 py-1 rounded-full" :class="statusClass(user.status)">{{ user.status }}</span>
                            </td>
                            <td class="px-4 py-3.5 text-xs text-gray-400">{{ user.joinedAt }}</td>
                            <td class="px-4 py-3.5">
                                <div class="flex items-center justify-end gap-1 opacity-0 group-hover:opacity-100 transition-opacity">
                                    <button @click="openUserModal(user)"
                                        class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100 hover:text-gray-600 transition" title="View profile">
                                        <Eye class="w-3.5 h-3.5" />
                                    </button>
                                    <button @click="openRoleModal(user)"
                                        class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-purple-50 hover:text-purple-600 transition" title="Change role">
                                        <ArrowLeftRight class="w-3.5 h-3.5" />
                                    </button>
                                    <button v-if="user.status === 'Pending'" @click="verifyUser(user.id)"
                                        class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-green-50 hover:text-green-600 transition" title="Verify KYC">
                                        <ShieldCheck class="w-3.5 h-3.5" />
                                    </button>
                                    <button v-if="user.status !== 'Banned'" @click="openSuspendModal(user)"
                                        class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-orange-50 hover:text-orange-500 transition" title="Suspend">
                                        <ShieldOff class="w-3.5 h-3.5" />
                                    </button>
                                    <button v-if="user.status === 'Banned'" @click="reinstateUser(user.id)"
                                        class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-green-50 hover:text-green-600 transition" title="Reinstate">
                                        <ShieldCheck class="w-3.5 h-3.5" />
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="flex items-center justify-between px-5 py-3 border-t border-gray-100">
                <p class="text-xs text-gray-400">
                    Showing <span class="font-semibold text-gray-700">{{ filteredUsers.length }}</span> of
                    <span class="font-semibold text-gray-700">{{ users.length }}</span> users
                </p>
                <div class="flex items-center gap-1">
                    <button v-for="pg in 3" :key="pg"
                        class="w-7 h-7 rounded-lg flex items-center justify-center text-xs font-medium transition"
                        :class="pg === 1 ? 'bg-green-600 text-white' : 'text-gray-400 hover:bg-gray-100'">{{ pg }}</button>
                </div>
            </div>
        </section>

        <!-- ── Moderation Goals ───────────────────────────────────────────────── -->
        <section class="grid grid-cols-3 gap-6">
            <div v-for="goal in moderationGoals" :key="goal.label" class="bg-white rounded-2xl shadow-sm border border-gray-100 p-5">
                <div class="flex items-center justify-between mb-3">
                    <p class="text-sm font-medium text-gray-700">{{ goal.label }}</p>
                    <span class="text-xs font-semibold" :style="{ color: goal.stroke }">{{ goal.percent }}%</span>
                </div>
                <div class="w-full bg-gray-100 rounded-full h-2 mb-2">
                    <div class="h-2 rounded-full transition-all duration-700" :style="{ width: goal.percent + '%', background: goal.stroke }"></div>
                </div>
                <div class="flex justify-between text-xs text-gray-400">
                    <span>{{ goal.current }}</span>
                    <span>Goal: {{ goal.target }}</span>
                </div>
            </div>
        </section>


        <!-- ══ MODALS ══════════════════════════════════════════════════════════ -->

        <!-- View User Profile Modal -->
        <Transition enter-active-class="transition-all duration-200 ease-out" enter-from-class="opacity-0"
            leave-active-class="transition-all duration-150 ease-in" leave-to-class="opacity-0">
            <div v-if="userModal.visible" class="fixed inset-0 z-50 flex items-center justify-center bg-black/30 backdrop-blur-sm"
                @click.self="userModal.visible = false">
                <div class="bg-white rounded-2xl shadow-xl w-full max-w-lg mx-4 overflow-hidden">
                    <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                        <h3 class="text-sm font-semibold text-gray-900">User Profile</h3>
                        <button @click="userModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4" />
                        </button>
                    </div>
                    <div v-if="userModal.user" class="p-5 space-y-4">
                        <!-- Header -->
                        <div class="flex items-center gap-4">
                            <div class="w-14 h-14 rounded-full flex items-center justify-center text-lg font-bold"
                                :class="roleAvatarClass(userModal.user.role)">
                                {{ initials(userModal.user.name) }}
                            </div>
                            <div class="flex-1">
                                <h2 class="text-base font-bold text-gray-900">{{ userModal.user.name }}</h2>
                                <p class="text-xs text-gray-400 font-mono">{{ userModal.user.email }}</p>
                                <div class="flex items-center gap-2 mt-1">
                                    <span class="text-xs font-medium px-2 py-0.5 rounded-full" :class="roleClass(userModal.user.role)">{{ userModal.user.role }}</span>
                                    <span class="text-xs font-medium px-2 py-0.5 rounded-full" :class="statusClass(userModal.user.status)">{{ userModal.user.status }}</span>
                                </div>
                            </div>
                            <div class="text-right">
                                <div class="text-2xl font-bold font-mono" :class="trustScoreColor(userModal.user.trustScore)">{{ userModal.user.trustScore }}</div>
                                <div class="text-xs text-gray-400">trust score</div>
                            </div>
                        </div>
                        <!-- Meta grid -->
                        <div class="grid grid-cols-3 gap-3">
                            <div class="bg-gray-50 rounded-xl p-3">
                                <p class="text-[11px] text-gray-400 mb-1">Province</p>
                                <p class="text-xs font-semibold text-gray-700">{{ userModal.user.province }}</p>
                            </div>
                            <div class="bg-gray-50 rounded-xl p-3">
                                <p class="text-[11px] text-gray-400 mb-1">Orders</p>
                                <p class="text-xs font-semibold text-gray-700">{{ userModal.user.orders }}</p>
                            </div>
                            <div class="bg-gray-50 rounded-xl p-3">
                                <p class="text-[11px] text-gray-400 mb-1">Rating</p>
                                <p class="text-xs font-semibold text-gray-700">{{ userModal.user.rating }}★</p>
                            </div>
                            <div class="bg-gray-50 rounded-xl p-3">
                                <p class="text-[11px] text-gray-400 mb-1">Joined</p>
                                <p class="text-xs font-semibold text-gray-700">{{ userModal.user.joinedAt }}</p>
                            </div>
                            <div class="bg-gray-50 rounded-xl p-3">
                                <p class="text-[11px] text-gray-400 mb-1">Disputes</p>
                                <p class="text-xs font-semibold text-gray-700">{{ userModal.user.disputes }}</p>
                            </div>
                            <div class="bg-gray-50 rounded-xl p-3">
                                <p class="text-[11px] text-gray-400 mb-1">User ID</p>
                                <p class="text-xs font-mono font-semibold text-gray-700">#USR-{{ String(userModal.user.id).padStart(4, '0') }}</p>
                            </div>
                        </div>
                        <div v-if="userModal.user.flagReason" class="bg-red-50 border border-red-100 rounded-xl p-3">
                            <p class="text-[11px] text-red-500 font-semibold mb-1">Flag Reason</p>
                            <p class="text-xs text-red-700">{{ userModal.user.flagReason }}</p>
                        </div>
                        <div class="flex gap-3 pt-1">
                            <button v-if="userModal.user.status === 'Pending'"
                                @click="() => { verifyUser(userModal.user.id); userModal.visible = false }"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl bg-green-600 hover:bg-green-700 text-white transition flex items-center justify-center gap-2">
                                <ShieldCheck class="w-4 h-4" /> Verify
                            </button>
                            <button v-if="userModal.user.status === 'Pending'"
                                @click="() => { userModal.visible = false; openRejectKycModal(userModal.user) }"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-red-200 text-red-500 hover:bg-red-50 transition flex items-center justify-center gap-2">
                                <X class="w-4 h-4" /> Reject KYC
                            </button>
                            <button v-if="userModal.user.status === 'Verified' || userModal.user.status === 'Active'"
                                @click="() => { openSuspendModal(userModal.user); userModal.visible = false }"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-orange-200 text-orange-600 hover:bg-orange-50 transition">
                                Suspend
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </Transition>

        <!-- Reject KYC Modal -->
        <Transition enter-active-class="transition-all duration-200 ease-out" enter-from-class="opacity-0"
            leave-active-class="transition-all duration-150 ease-in" leave-to-class="opacity-0">
            <div v-if="rejectKycModal.visible" class="fixed inset-0 z-50 flex items-center justify-center bg-black/30 backdrop-blur-sm"
                @click.self="rejectKycModal.visible = false">
                <div class="bg-white rounded-2xl shadow-xl w-full max-w-md mx-4">
                    <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                        <div class="flex items-center gap-3">
                            <div class="w-8 h-8 rounded-xl bg-red-100 flex items-center justify-center">
                                <ShieldX class="w-4 h-4 text-red-500" />
                            </div>
                            <div>
                                <p class="text-sm font-semibold text-gray-900">Reject KYC</p>
                                <p class="text-xs text-gray-400">{{ rejectKycModal.user?.name }}</p>
                            </div>
                        </div>
                        <button @click="rejectKycModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4" />
                        </button>
                    </div>
                    <div class="p-5 space-y-4">
                        <div>
                            <label class="text-xs font-semibold text-gray-700 block mb-2">Rejection Reason</label>
                            <div class="grid grid-cols-2 gap-2 mb-3">
                                <button v-for="reason in kycRejectionReasons" :key="reason"
                                    @click="rejectKycModal.reason = reason"
                                    :class="['text-xs font-medium px-3 py-2 rounded-xl border transition text-left',
                                        rejectKycModal.reason === reason
                                            ? 'border-red-400 bg-red-50 text-red-700'
                                            : 'border-gray-200 text-gray-600 hover:border-gray-300']">
                                    {{ reason }}
                                </button>
                            </div>
                            <textarea v-model="rejectKycModal.note" rows="3"
                                placeholder="Add additional notes for the user (optional)…"
                                class="w-full px-3 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-400 transition placeholder:text-gray-300 resize-none"></textarea>
                        </div>
                        <div class="flex items-center gap-2 p-3 bg-amber-50 rounded-xl border border-amber-100">
                            <AlertCircle class="w-4 h-4 text-amber-500 flex-shrink-0" />
                            <p class="text-xs text-amber-700">The user will be notified with the reason you provide.</p>
                        </div>
                        <div class="flex gap-3">
                            <button @click="rejectKycModal.visible = false" class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50 transition">Cancel</button>
                            <button @click="executeRejectKyc" :disabled="!rejectKycModal.reason"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl bg-red-500 hover:bg-red-600 text-white transition disabled:opacity-40 disabled:cursor-not-allowed">
                                Send Rejection
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </Transition>

        <!-- Role Change Modal -->
        <Transition enter-active-class="transition-all duration-200 ease-out" enter-from-class="opacity-0"
            leave-active-class="transition-all duration-150 ease-in" leave-to-class="opacity-0">
            <div v-if="roleModal.visible" class="fixed inset-0 z-50 flex items-center justify-center bg-black/30 backdrop-blur-sm"
                @click.self="roleModal.visible = false">
                <div class="bg-white rounded-2xl shadow-xl w-full max-w-sm mx-4">
                    <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                        <div class="flex items-center gap-3">
                            <div class="w-8 h-8 rounded-xl bg-purple-100 flex items-center justify-center">
                                <ArrowLeftRight class="w-4 h-4 text-purple-500" />
                            </div>
                            <div>
                                <p class="text-sm font-semibold text-gray-900">Change Role</p>
                                <p class="text-xs text-gray-400">{{ roleModal.user?.name }}</p>
                            </div>
                        </div>
                        <button @click="roleModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4" />
                        </button>
                    </div>
                    <div class="p-5 space-y-4">
                        <div class="flex items-center gap-3 p-3 bg-gray-50 rounded-xl">
                            <span class="text-xs font-semibold text-gray-700">Current:</span>
                            <span class="text-xs font-medium px-2 py-0.5 rounded-full" :class="roleClass(roleModal.user?.role)">{{ roleModal.user?.role }}</span>
                        </div>
                        <div>
                            <label class="text-xs font-semibold text-gray-700 block mb-2">Assign New Role</label>
                            <div class="space-y-2">
                                <button v-for="r in roles" :key="r"
                                    @click="roleModal.newRole = r"
                                    :disabled="r === roleModal.user?.role"
                                    :class="['w-full text-xs font-medium px-3 py-2.5 rounded-xl border transition text-left flex items-center gap-2',
                                        roleModal.newRole === r
                                            ? 'border-purple-400 bg-purple-50 text-purple-700'
                                            : r === roleModal.user?.role
                                                ? 'border-gray-100 text-gray-300 cursor-not-allowed'
                                                : 'border-gray-200 text-gray-600 hover:border-gray-300']">
                                    <span class="w-2 h-2 rounded-full flex-shrink-0"
                                        :class="r === 'Farmer' ? 'bg-green-400' : r === 'Buyer' ? 'bg-blue-400' : 'bg-purple-400'"></span>
                                    {{ r }}
                                    <span v-if="r === roleModal.user?.role" class="ml-auto text-[10px] text-gray-300">(current)</span>
                                </button>
                            </div>
                        </div>
                        <div class="flex gap-3">
                            <button @click="roleModal.visible = false" class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50 transition">Cancel</button>
                            <button @click="executeRoleChange" :disabled="!roleModal.newRole"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl bg-purple-600 hover:bg-purple-700 text-white transition disabled:opacity-40 disabled:cursor-not-allowed">
                                Confirm Change
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </Transition>

        <!-- Suspend / Ban Modal -->
        <Transition enter-active-class="transition-all duration-200 ease-out" enter-from-class="opacity-0"
            leave-active-class="transition-all duration-150 ease-in" leave-to-class="opacity-0">
            <div v-if="suspendModal.visible" class="fixed inset-0 z-50 flex items-center justify-center bg-black/30 backdrop-blur-sm"
                @click.self="suspendModal.visible = false">
                <div class="bg-white rounded-2xl shadow-xl w-full max-w-md mx-4">
                    <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                        <div class="flex items-center gap-3">
                            <div class="w-8 h-8 rounded-xl bg-orange-100 flex items-center justify-center">
                                <ShieldOff class="w-4 h-4 text-orange-500" />
                            </div>
                            <div>
                                <p class="text-sm font-semibold text-gray-900">Suspend / Ban User</p>
                                <p class="text-xs text-gray-400">{{ suspendModal.user?.name }}</p>
                            </div>
                        </div>
                        <button @click="suspendModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4" />
                        </button>
                    </div>
                    <div class="p-5 space-y-4">
                        <div class="grid grid-cols-2 gap-3">
                            <button @click="suspendModal.action = 'Suspended'"
                                :class="['p-3 rounded-xl border-2 transition text-left',
                                    suspendModal.action === 'Suspended' ? 'border-orange-400 bg-orange-50' : 'border-gray-200 hover:border-gray-300']">
                                <p class="text-xs font-semibold text-gray-800">Suspend</p>
                                <p class="text-[11px] text-gray-400 mt-0.5">Temporary · user can appeal</p>
                            </button>
                            <button @click="suspendModal.action = 'Banned'"
                                :class="['p-3 rounded-xl border-2 transition text-left',
                                    suspendModal.action === 'Banned' ? 'border-red-400 bg-red-50' : 'border-gray-200 hover:border-gray-300']">
                                <p class="text-xs font-semibold text-gray-800">Ban</p>
                                <p class="text-[11px] text-gray-400 mt-0.5">Permanent · full access removed</p>
                            </button>
                        </div>
                        <div>
                            <label class="text-xs font-semibold text-gray-700 block mb-2">Reason</label>
                            <div class="grid grid-cols-2 gap-2 mb-3">
                                <button v-for="reason in suspendReasons" :key="reason"
                                    @click="suspendModal.reason = reason"
                                    :class="['text-xs font-medium px-3 py-2 rounded-xl border transition text-left',
                                        suspendModal.reason === reason
                                            ? 'border-orange-400 bg-orange-50 text-orange-700'
                                            : 'border-gray-200 text-gray-600 hover:border-gray-300']">
                                    {{ reason }}
                                </button>
                            </div>
                            <textarea v-model="suspendModal.note" rows="2"
                                placeholder="Additional notes (optional)…"
                                class="w-full px-3 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-orange-500/20 focus:border-orange-400 transition placeholder:text-gray-300 resize-none"></textarea>
                        </div>
                        <div class="flex gap-3">
                            <button @click="suspendModal.visible = false" class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50 transition">Cancel</button>
                            <button @click="executeSuspend" :disabled="!suspendModal.reason || !suspendModal.action"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl bg-orange-500 hover:bg-orange-600 text-white transition disabled:opacity-40 disabled:cursor-not-allowed">
                                Confirm Action
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </Transition>

        <!-- Invite User Modal -->
        <Transition enter-active-class="transition-all duration-200 ease-out" enter-from-class="opacity-0"
            leave-active-class="transition-all duration-150 ease-in" leave-to-class="opacity-0">
            <div v-if="inviteModal.visible" class="fixed inset-0 z-50 flex items-center justify-center bg-black/30 backdrop-blur-sm"
                @click.self="inviteModal.visible = false">
                <div class="bg-white rounded-2xl shadow-xl w-full max-w-sm mx-4">
                    <div class="flex items-center justify-between px-5 py-4 border-b border-gray-100">
                        <div class="flex items-center gap-3">
                            <div class="w-8 h-8 rounded-xl bg-green-100 flex items-center justify-center">
                                <UserPlus class="w-4 h-4 text-green-600" />
                            </div>
                            <p class="text-sm font-semibold text-gray-900">Invite New User</p>
                        </div>
                        <button @click="inviteModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4" />
                        </button>
                    </div>
                    <div class="p-5 space-y-3">
                        <div>
                            <label class="text-xs font-semibold text-gray-700 block mb-1.5">Email address</label>
                            <input v-model="inviteModal.email" type="email" placeholder="farmer@example.com"
                                class="w-full px-3 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 transition" />
                        </div>
                        <div>
                            <label class="text-xs font-semibold text-gray-700 block mb-1.5">Assign role</label>
                            <div class="flex gap-2">
                                <button v-for="r in roles" :key="r" @click="inviteModal.role = r"
                                    :class="['flex-1 text-xs font-medium py-2 rounded-xl border transition',
                                        inviteModal.role === r ? 'border-green-400 bg-green-50 text-green-700' : 'border-gray-200 text-gray-600 hover:border-gray-300']">
                                    {{ r }}
                                </button>
                            </div>
                        </div>
                        <div class="flex gap-3 pt-1">
                            <button @click="inviteModal.visible = false" class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50 transition">Cancel</button>
                            <button @click="sendInvite" :disabled="!inviteModal.email || !inviteModal.role"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl bg-green-600 hover:bg-green-700 text-white transition disabled:opacity-40 disabled:cursor-not-allowed flex items-center justify-center gap-2">
                                <Mail class="w-4 h-4" /> Send Invite
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </Transition>

        <!-- ── Toast ─────────────────────────────────────────────────────────── -->
        <Transition enter-active-class="transition-all duration-300 ease-out" enter-from-class="opacity-0 translate-y-2"
            leave-active-class="transition-all duration-200 ease-in" leave-to-class="opacity-0 translate-y-2">
            <div v-if="toast.visible"
                class="fixed bottom-6 right-6 z-[60] flex items-center gap-3 px-4 py-3 rounded-2xl shadow-lg text-sm font-medium text-white min-w-[260px]"
                :class="{
                    'bg-gray-900':    toast.type === 'success',
                    'bg-red-500':     toast.type === 'error',
                    'bg-amber-500':   toast.type === 'warning',
                    'bg-purple-600':  toast.type === 'info',
                }">
                <component :is="toast.type === 'success' ? CheckCheck : toast.type === 'warning' ? AlertCircle : toast.type === 'info' ? ArrowLeftRight : X"
                    class="w-4 h-4 flex-shrink-0" />
                {{ toast.message }}
            </div>
        </Transition>

    </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import {
    Users, Download, Search, Eye, X, Mail,
    ShieldCheck, ShieldOff, ShieldX, ShieldAlert,
    BadgeCheck, CheckCheck, AlertCircle,
    UserPlus, UserX,
    ArrowLeftRight, MapPin, TrendingDown,
    Tractor, ShoppingBag, FileSearch,
} from 'lucide-vue-next'

definePageMeta({ layout: 'admin' })

// ── Users Data ────────────────────────────────────────────────────────────────
const users = ref([
    { id: 1,  name: 'Sophea Keo',   email: 'sophea@farm.kh',        role: 'Farmer', province: 'Kampong Cham', trustScore: 94, status: 'Active',    flagged: false, flagReason: null,                    orders: 96, rating: 4.9, disputes: 0, joinedAt: 'Mar 12', submittedKycAt: 'Mar 10', docsComplete: true  },
    { id: 2,  name: 'Dara Pich',    email: 'dara.pich@gmail.com',   role: 'Farmer', province: 'Kampot',       trustScore: 88, status: 'Active',    flagged: false, flagReason: null,                    orders: 68, rating: 4.7, disputes: 1, joinedAt: 'Feb 28', submittedKycAt: 'Feb 26', docsComplete: true  },
    { id: 3,  name: 'Bopha Lim',    email: 'bophalim@yahoo.com',    role: 'Farmer', province: 'Siem Reap',    trustScore: 73, status: 'Active',    flagged: true,  flagReason: 'Duplicate account suspected', orders: 54, rating: 4.6, disputes: 1, joinedAt: 'Jan 5',  submittedKycAt: 'Jan 3',  docsComplete: true  },
    { id: 4,  name: 'Kosal Rith',   email: 'kosal.rith@buy.kh',     role: 'Buyer',  province: 'Phnom Penh',  trustScore: 24, status: 'Suspended', flagged: true,  flagReason: 'Suspicious order pattern',      orders: 12, rating: 2.1, disputes: 5, joinedAt: 'Dec 14', submittedKycAt: 'Dec 12', docsComplete: false },
    { id: 5,  name: 'Sreymom Chan', email: 'sreymom@farm.kh',       role: 'Farmer', province: 'Kratie',       trustScore: 81, status: 'Active',    flagged: true,  flagReason: 'Identity dispute filed',        orders: 54, rating: 4.6, disputes: 2, joinedAt: 'Nov 30', submittedKycAt: 'Nov 28', docsComplete: true  },
    { id: 6,  name: 'Virak Nou',    email: 'virak.nou@gmail.com',   role: 'Farmer', province: 'Kandal',       trustScore: 79, status: 'Active',    flagged: false, flagReason: null,                    orders: 43, rating: 4.4, disputes: 1, joinedAt: 'Oct 22', submittedKycAt: 'Oct 20', docsComplete: true  },
    { id: 7,  name: 'Maly Rith',    email: 'maly.rith@shop.kh',     role: 'Buyer',  province: 'Phnom Penh',  trustScore: 67, status: 'Pending',   flagged: false, flagReason: null,                    orders: 8,  rating: 4.0, disputes: 0, joinedAt: 'Apr 19', submittedKycAt: 'Apr 18', docsComplete: true  },
    { id: 8,  name: 'Dara Nou',     email: 'dara.nou@agri.kh',      role: 'Farmer', province: 'Kampong Cham', trustScore: 55, status: 'Pending',   flagged: false, flagReason: null,                    orders: 4,  rating: 4.1, disputes: 0, joinedAt: 'Apr 19', submittedKycAt: 'Apr 17', docsComplete: false },
    { id: 9,  name: 'Noun Visal',   email: 'noun.visal@outlook.com',role: 'Farmer', province: 'Battambang',   trustScore: 40, status: 'Active',    flagged: false, flagReason: null,                    orders: 9,  rating: 3.8, disputes: 1, joinedAt: 'Sep 5',  submittedKycAt: 'Sep 3',  docsComplete: true,  daysSince: 62, churnRisk: 'High' },
    { id: 10, name: 'Pisey Sok',    email: 'pisey.sok@gmail.com',   role: 'Buyer',  province: 'Phnom Penh',  trustScore: 52, status: 'Active',    flagged: false, flagReason: null,                    orders: 21, rating: 4.2, disputes: 0, joinedAt: 'Jul 18', submittedKycAt: 'Jul 16', docsComplete: true,  daysSince: 51, churnRisk: 'High' },
    { id: 11, name: 'Thy Heng',     email: 'thy.heng@farm.kh',      role: 'Farmer', province: 'Kampot',       trustScore: 60, status: 'Active',    flagged: false, flagReason: null,                    orders: 17, rating: 4.3, disputes: 0, joinedAt: 'Aug 10', submittedKycAt: 'Aug 8',  docsComplete: true,  daysSince: 44, churnRisk: 'Medium' },
    { id: 12, name: 'Bopha Chan',   email: 'bchan@buyer.kh',        role: 'Buyer',  province: 'Phnom Penh',  trustScore: 71, status: 'Pending',   flagged: false, flagReason: null,                    orders: 0,  rating: 0,   disputes: 0, joinedAt: 'Apr 17', submittedKycAt: 'Apr 15', docsComplete: true  },
])

// ── Roles & Statuses ──────────────────────────────────────────────────────────
const roles      = ['Farmer', 'Buyer', 'Admin']
const statusTabs = ['All', 'Pending', 'Active', 'Suspended', 'Banned']

// ── Computed ──────────────────────────────────────────────────────────────────
const countByRole   = (r) => users.value.filter(u => u.role === r).length
const countByStatus = (s) => users.value.filter(u => u.status === s).length

const pendingKycCount = computed(() => users.value.filter(u => u.status === 'Pending').length)
const pendingKycUsers = computed(() => users.value.filter(u => u.status === 'Pending'))
const flaggedUsers    = computed(() => users.value.filter(u => u.flagged))

const avgTrustScore = computed(() => {
    const sum = users.value.reduce((a, u) => a + u.trustScore, 0)
    return Math.round(sum / users.value.length)
})

const trustLeaderboard = computed(() =>
    [...users.value].sort((a, b) => b.trustScore - a.trustScore).slice(0, 5)
)

const churnRiskUsers = computed(() =>
    users.value.filter(u => u.daysSince).sort((a, b) => b.daysSince - a.daysSince)
)

const churnCounts = computed(() => ({
    high: churnRiskUsers.value.filter(u => u.churnRisk === 'High').length,
    med:  churnRiskUsers.value.filter(u => u.churnRisk === 'Medium').length,
    low:  churnRiskUsers.value.filter(u => u.churnRisk === 'Low').length,
}))

// ── Filters ───────────────────────────────────────────────────────────────────
const searchQuery  = ref('')
const filterStatus = ref('All')
const filterRole   = ref('')
const filterTrust  = ref('')

const filteredUsers = computed(() =>
    users.value.filter(u => {
        const q  = searchQuery.value.toLowerCase()
        const ms = u.name.toLowerCase().includes(q) || u.email.toLowerCase().includes(q)
        const mr = !filterRole.value || u.role === filterRole.value
        const mv = filterStatus.value === 'All' || u.status === filterStatus.value
        const mt = !filterTrust.value ||
            (filterTrust.value === 'high' && u.trustScore >= 80) ||
            (filterTrust.value === 'mid'  && u.trustScore >= 50 && u.trustScore < 80) ||
            (filterTrust.value === 'low'  && u.trustScore < 50)
        return ms && mr && mv && mt
    })
)

// ── Geography Data ────────────────────────────────────────────────────────────
const geoData = ref([
    { province: 'Phnom Penh',    count: 412, pct: 32 },
    { province: 'Kampong Cham',  count: 198, pct: 15 },
    { province: 'Siem Reap',     count: 156, pct: 12 },
    { province: 'Kampot',        count: 110, pct: 9  },
    { province: 'Kratie',        count: 84,  pct: 7  },
    { province: 'Kandal',        count: 68,  pct: 5  },
    { province: 'Other',         count: 256, pct: 20 },
])

// ── Audit Log ─────────────────────────────────────────────────────────────────
const auditLog = ref([
    { id: 1, time: '05:22', user: 'Maly Rith',   from: 'Buyer',     to: 'Farmer',    by: 'admin01' },
    { id: 2, time: '06:47', user: 'Kosal Rith',  from: 'Farmer',    to: 'Suspended', by: 'admin02' },
    { id: 3, time: '14:03', user: 'Thy Heng',    from: 'Suspended', to: 'Farmer',    by: 'admin01' },
    { id: 4, time: '11:15', user: 'Bopha Lim',   from: 'Buyer',     to: 'Admin',     by: 'superadmin' },
])

function addAuditLog(user, from, to) {
    auditLog.value.unshift({ id: Date.now(), time: new Date().toLocaleTimeString('en-GB',{hour:'2-digit',minute:'2-digit'}), user, from, to, by: 'admin01' })
    if (auditLog.value.length > 8) auditLog.value.pop()
}

// ── User Activity ─────────────────────────────────────────────────────────────
const userActivity = ref([
    { id: 1, text: 'Sophea Keo verified and activated as farmer',     time: '3 min ago',  dotColor: 'bg-green-500' },
    { id: 2, text: 'Dara Pich submitted KYC documents for review',   time: '45 min ago', dotColor: 'bg-amber-400' },
    { id: 3, text: 'Kosal Rith suspended — payment dispute',         time: '2 hrs ago',  dotColor: 'bg-red-500'   },
    { id: 4, text: 'Maly Rith role changed Buyer → Farmer',          time: '3 hrs ago',  dotColor: 'bg-purple-500'},
    { id: 5, text: 'Virak Pov flagged for suspicious order pattern', time: '5 hrs ago',  dotColor: 'bg-red-400'   },
    { id: 6, text: 'Bopha Chan submitted KYC, awaiting review',      time: '8 hrs ago',  dotColor: 'bg-amber-400' },
])

function addActivity(text, color = 'bg-green-500') {
    userActivity.value.unshift({ id: Date.now(), text, time: 'Just now', dotColor: color })
    if (userActivity.value.length > 8) userActivity.value.pop()
}

// ── Growth Trend Chart ────────────────────────────────────────────────────────
const TW = 600; const TH = 140
const trendDays = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
const trendData = {
    farmers: [2, 4, 3, 6, 5, 8, 4],
    buyers:  [3, 5, 6, 4, 7, 5, 9],
}
const trendMin = 0; const trendMax = 20

function trendYs(v) { return 10 + (1 - (v - trendMin) / (trendMax - trendMin)) * (TH - 20) }

function trendLinePath(key) {
    const pts = trendData[key].map((v, i) => ({ x: (i / (trendData[key].length - 1)) * TW, y: trendYs(v) }))
    let d = `M ${pts[0].x} ${pts[0].y}`
    for (let i = 0; i < pts.length - 1; i++) {
        const p0 = pts[Math.max(i-1,0)], p1 = pts[i], p2 = pts[i+1], p3 = pts[Math.min(i+2,pts.length-1)]
        d += ` C ${p1.x+(p2.x-p0.x)/6} ${p1.y+(p2.y-p0.y)/6}, ${p2.x-(p3.x-p1.x)/6} ${p2.y-(p3.y-p1.y)/6}, ${p2.x} ${p2.y}`
    }
    return d
}
function trendAreaPath(key) {
    const pts = trendData[key].map((v, i) => ({ x: (i / (trendData[key].length - 1)) * TW, y: trendYs(v) }))
    return `${trendLinePath(key)} L ${pts[pts.length-1].x} ${TH} L ${pts[0].x} ${TH} Z`
}

// ── Moderation Goals ──────────────────────────────────────────────────────────
const moderationGoals = ref([
    { label: 'KYC Approval Rate',   current: '84 verified',    target: '100 verified',   percent: 84, stroke: '#22c55e' },
    { label: 'Avg Review Time',     current: 'Avg 1.8 days',   target: 'Under 1 day',    percent: 55, stroke: '#3b82f6' },
    { label: 'Flag Resolution',     current: '3 resolved',     target: '5 this month',   percent: 60, stroke: '#f59e0b' },
])

// ── Actions ───────────────────────────────────────────────────────────────────
const processingId = ref(null)

function verifyUser(id) {
    const u = users.value.find(x => x.id === id)
    if (!u) return
    processingId.value = id
    setTimeout(() => {
        u.status = 'Active'
        processingId.value = null
        addActivity(`${u.name} verified and activated`, 'bg-green-500')
        showToast(`${u.name} has been verified`, 'success')
    }, 400)
}

function bulkApproveKyc() {
    const pending = users.value.filter(u => u.status === 'Pending' && u.docsComplete)
    pending.forEach(u => { u.status = 'Active' })
    addActivity(`${pending.length} users bulk-verified`, 'bg-green-500')
    showToast(`${pending.length} users verified in bulk`, 'success')
}

function resolveFlag(id) {
    const u = users.value.find(x => x.id === id)
    if (!u) return
    u.flagged = false
    addActivity(`Flag on ${u.name} resolved`, 'bg-blue-500')
    showToast(`Flag on ${u.name} resolved`, 'success')
}

function banUser(id) {
    const u = users.value.find(x => x.id === id)
    if (!u) return
    u.status = 'Banned'; u.flagged = false
    addActivity(`${u.name} banned`, 'bg-red-500')
    showToast(`${u.name} has been banned`, 'error')
}

function reinstateUser(id) {
    const u = users.value.find(x => x.id === id)
    if (!u) return
    u.status = 'Active'
    addActivity(`${u.name} reinstated`, 'bg-green-500')
    showToast(`${u.name} has been reinstated`, 'success')
}

function nudgeUser(id) {
    const u = users.value.find(x => x.id === id)
    showToast(`Re-engagement email sent to ${u?.name}`, 'info')
}

function sendReengagement() {
    showToast('Re-engagement campaign drafted — check Messages', 'info')
}

function exportCSV() { showToast('Exporting users to CSV…', 'success') }
function exportAuditLog() { showToast('Exporting audit log…', 'success') }

// ── Reject KYC Modal ──────────────────────────────────────────────────────────
const kycRejectionReasons = [
    'Blurry / unreadable ID',
    'ID name mismatch',
    'Expired document',
    'Missing land certificate',
    'Incomplete selfie',
    'Duplicate account',
]
const rejectKycModal = ref({ visible: false, user: null, reason: '', note: '' })
function openRejectKycModal(user) { rejectKycModal.value = { visible: true, user, reason: '', note: '' } }
function executeRejectKyc() {
    const u = users.value.find(x => x.id === rejectKycModal.value.user?.id)
    if (!u) return
    u.status = 'Pending'
    addActivity(`${u.name} KYC rejected — ${rejectKycModal.value.reason}`, 'bg-red-500')
    showToast(`${u.name} KYC rejected. User has been notified.`, 'warning')
    rejectKycModal.value.visible = false
}

// ── Role Modal ────────────────────────────────────────────────────────────────
const roleModal = ref({ visible: false, user: null, newRole: '' })
function openRoleModal(user) { roleModal.value = { visible: true, user, newRole: '' } }
function executeRoleChange() {
    const u = users.value.find(x => x.id === roleModal.value.user?.id)
    if (!u || !roleModal.value.newRole) return
    const oldRole = u.role
    u.role = roleModal.value.newRole
    addAuditLog(u.name, oldRole, roleModal.value.newRole)
    addActivity(`${u.name} role changed ${oldRole} → ${roleModal.value.newRole}`, 'bg-purple-500')
    showToast(`${u.name} is now a ${roleModal.value.newRole}`, 'info')
    roleModal.value.visible = false
}

// ── Suspend Modal ─────────────────────────────────────────────────────────────
const suspendReasons = ['Payment dispute', 'Policy violation', 'Spam / fake listing', 'Fraud suspected', 'User request', 'Duplicate account']
const suspendModal = ref({ visible: false, user: null, action: '', reason: '', note: '' })
function openSuspendModal(user) { suspendModal.value = { visible: true, user, action: '', reason: '', note: '' } }
function executeSuspend() {
    const u = users.value.find(x => x.id === suspendModal.value.user?.id)
    if (!u) return
    u.status = suspendModal.value.action
    addActivity(`${u.name} ${suspendModal.value.action.toLowerCase()} — ${suspendModal.value.reason}`, 'bg-orange-400')
    showToast(`${u.name} has been ${suspendModal.value.action.toLowerCase()}`, 'warning')
    suspendModal.value.visible = false
}

// ── Invite Modal ──────────────────────────────────────────────────────────────
const inviteModal = ref({ visible: false, email: '', role: '' })
function openInviteModal() { inviteModal.value = { visible: true, email: '', role: '' } }
function sendInvite() {
    showToast(`Invite sent to ${inviteModal.value.email} as ${inviteModal.value.role}`, 'success')
    inviteModal.value.visible = false
}

// ── View User Modal ───────────────────────────────────────────────────────────
const userModal = ref({ visible: false, user: null })
function openUserModal(user) { userModal.value = { visible: true, user } }

// ── Toast ─────────────────────────────────────────────────────────────────────
const toast = ref({ visible: false, message: '', type: 'success' })
let toastTimer = null
function showToast(message, type = 'success') {
    if (toastTimer) clearTimeout(toastTimer)
    toast.value = { visible: true, message, type }
    toastTimer  = setTimeout(() => { toast.value.visible = false }, 3200)
}

// ── Style Helpers ─────────────────────────────────────────────────────────────
const initials = (name) => name.split(' ').map(n => n[0]).join('')

const roleAvatarClass = (r) => ({
    Farmer:    'bg-green-100 text-green-700',
    Buyer:     'bg-blue-100 text-blue-700',
    Admin:     'bg-purple-100 text-purple-700',
    Suspended: 'bg-orange-100 text-orange-700',
}[r] ?? 'bg-gray-100 text-gray-600')

const roleClass = (r) => ({
    Farmer:    'bg-green-100 text-green-700',
    Buyer:     'bg-blue-100 text-blue-700',
    Admin:     'bg-purple-100 text-purple-700',
    Suspended: 'bg-orange-100 text-orange-600',
}[r] ?? 'bg-gray-100 text-gray-600')

const statusClass = (s) => ({
    Active:    'bg-green-100 text-green-700',
    Pending:   'bg-amber-100 text-amber-700',
    Suspended: 'bg-orange-100 text-orange-700',
    Banned:    'bg-red-100 text-red-600',
}[s] ?? 'bg-gray-100 text-gray-600')

const trustScoreColor = (s) => s >= 80 ? 'text-green-600' : s >= 50 ? 'text-amber-600' : 'text-red-500'
const trustScoreBg    = (s) => s >= 80 ? '#16a34a'        : s >= 50 ? '#d97706'        : '#dc2626'

const churnRiskClass = (r) => ({
    High:   'bg-red-100 text-red-600',
    Medium: 'bg-amber-100 text-amber-600',
    Low:    'bg-green-100 text-green-600',
}[r] ?? 'bg-gray-100 text-gray-600')
</script>