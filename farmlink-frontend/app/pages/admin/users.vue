<template>
    <div class="p-6 space-y-6">

        <div class="flex items-center justify-between">
            <div>
                <h1 class="text-xl font-bold text-gray-900">Users Management</h1>
                <p class="text-sm text-gray-400 mt-0.5">Manage and view all consumer users</p>
            </div>
            <div class="flex items-center gap-2">
                <button
                    @click="viewOrders"
                    class="flex items-center gap-2 border border-gray-200 text-gray-600 hover:bg-gray-50 text-sm font-medium px-4 py-2.5 rounded-xl transition-colors"
                >
                    <ShoppingBag class="w-4 h-4" /> View Orders
                </button>
            </div>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
            <!-- Total Users Card -->
            <div class="bg-white rounded-2xl border border-gray-100 p-4 shadow-sm hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-xs text-gray-500 font-medium mb-1">Total Users</p>
                        <p class="text-2xl font-bold text-gray-900">{{ users.length }}</p>
                    </div>
                    <div class="w-12 h-12 rounded-xl bg-blue-100 flex items-center justify-center flex-shrink-0">
                        <Users class="w-6 h-6 text-blue-600"/>
                    </div>
                </div>
                <p class="text-xs text-gray-400 mt-2">Active consumers</p>
            </div>

            <!-- Active Users Card -->
            <div class="bg-white rounded-2xl border border-gray-100 p-4 shadow-sm hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-xs text-gray-500 font-medium mb-1">Active Users</p>
                        <p class="text-2xl font-bold text-gray-900">{{ countBuyerByStatus('Active') }}</p>
                    </div>
                    <div class="w-12 h-12 rounded-xl bg-green-100 flex items-center justify-center flex-shrink-0">
                        <ShieldCheck class="w-6 h-6 text-green-600"/>
                    </div>
                </div>
                <p class="text-xs text-gray-400 mt-2">Verified & active</p>
            </div>

            <!-- Pending KYC Card -->
            <div class="bg-white rounded-2xl border border-gray-100 p-4 shadow-sm hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-xs text-gray-500 font-medium mb-1">Pending KYC</p>
                        <p class="text-2xl font-bold text-gray-900">{{ pendingBuyerKycCount }}</p>
                    </div>
                    <div class="w-12 h-12 rounded-xl bg-amber-100 flex items-center justify-center flex-shrink-0">
                        <AlertCircle class="w-6 h-6 text-amber-600"/>
                    </div>
                </div>
                <p class="text-xs text-gray-400 mt-2">Awaiting verification</p>
            </div>

            <!-- Avg Trust Score Card -->
            <div class="bg-white rounded-2xl border border-gray-100 p-4 shadow-sm hover:shadow-md transition-shadow">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-xs text-gray-500 font-medium mb-1">Avg Trust Score</p>
                        <p class="text-2xl font-bold text-gray-900">{{ avgBuyerTrustScore }}/100</p>
                    </div>
                    <div class="w-12 h-12 rounded-xl bg-purple-100 flex items-center justify-center flex-shrink-0">
                        <TrendingUp class="w-6 h-6 text-purple-600"/>
                    </div>
                </div>
                <p class="text-xs text-gray-400 mt-2">Community average</p>
            </div>
        </div>

        <!-- ── Users Table ────────────────────────────────────────────────────────── -->
        <section class="bg-white rounded-2xl shadow-sm border border-gray-100">
            <!-- Search & Filter Bar -->
            <div class="px-5 py-4 border-b border-gray-100 space-y-4">
                <!-- Search Bar -->
                <div class="relative flex-1 min-w-[200px]">
                    <Search class="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-300"/>
                    <input v-model="searchQuery" type="text" placeholder="Search by name or email…"
                        class="w-full pl-9 pr-4 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 transition"/>
                </div>

                <!-- Filter Options -->
                <div class="grid grid-cols-1 md:grid-cols-4 gap-3">
                    <!-- Status Filter -->
                    <div>
                        <label class="text-xs font-semibold text-gray-600 block mb-2">Status</label>
                        <select v-model="filterStatus"
                            class="w-full px-3 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 transition">
                            <option value="">All Status</option>
                            <option value="Active">Active</option>
                            <option value="Pending">Pending</option>
                            <option value="Suspended">Suspended</option>
                            <option value="Banned">Banned</option>
                        </select>
                    </div>

                    <!-- Trust Score Filter -->
                    <div>
                        <label class="text-xs font-semibold text-gray-600 block mb-2">Trust Score</label>
                        <select v-model="filterTrust"
                            class="w-full px-3 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 transition">
                            <option value="">All Scores</option>
                            <option value="high">High (80+)</option>
                            <option value="mid">Medium (50-79)</option>
                            <option value="low">Low (0-49)</option>
                        </select>
                    </div>

                    <!-- Sort -->
                    <div>
                        <label class="text-xs font-semibold text-gray-600 block mb-2">Sort By</label>
                        <select v-model="sortBy"
                            class="w-full px-3 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500/20 focus:border-green-400 transition">
                            <option value="name">Name (A-Z)</option>
                            <option value="trust-desc">Trust Score (High-Low)</option>
                            <option value="trust-asc">Trust Score (Low-High)</option>
                            <option value="orders-desc">Orders (Most)</option>
                            <option value="rating-desc">Rating (Highest)</option>
                        </select>
                    </div>

                    <!-- Reset Filters -->
                    <div class="flex items-end">
                        <button @click="resetFilters"
                            class="w-full px-3 py-2 text-sm border border-gray-300 text-gray-600 rounded-xl hover:bg-gray-50 font-medium transition">
                            Reset Filters
                        </button>
                    </div>
                </div>
            </div>

            <!-- Table -->
            <div class="overflow-x-auto">
                <table class="w-full">
                    <thead>
                        <tr class="border-b border-gray-100 bg-gray-50">
                            <th class="px-5 py-3 text-left text-xs font-semibold text-gray-600">User</th>
                            <th class="px-5 py-3 text-left text-xs font-semibold text-gray-600">Email</th>
                            <th class="px-5 py-3 text-left text-xs font-semibold text-gray-600">Status</th>
                            <th class="px-5 py-3 text-center text-xs font-semibold text-gray-600">Trust</th>
                            <th class="px-5 py-3 text-center text-xs font-semibold text-gray-600">Orders</th>
                            <th class="px-5 py-3 text-center text-xs font-semibold text-gray-600">Rating</th>
                            <th class="px-5 py-3 text-left text-xs font-semibold text-gray-600">Province</th>
                            <th class="px-5 py-3 text-center text-xs font-semibold text-gray-600">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-if="filteredUsers.length === 0">
                            <td colspan="8" class="px-5 py-12">
                                <div class="flex flex-col items-center gap-3">
                                    <div class="w-14 h-14 rounded-2xl bg-gray-50 flex items-center justify-center">
                                        <UserX class="w-6 h-6 text-gray-300"/>
                                    </div>
                                    <p class="text-sm font-medium text-gray-500">No users found</p>
                                </div>
                            </td>
                        </tr>
                        <tr v-for="user in filteredUsers" :key="user.id" class="border-b border-gray-100 hover:bg-gray-50 transition-colors">
                            <td class="px-5 py-4">
                                <div class="flex items-center gap-3">
                                    <div class="w-10 h-10 rounded-full flex items-center justify-center text-sm font-bold flex-shrink-0"
                                        :class="roleAvatarClass(user.role)">
                                        {{ initials(user.name) }}
                                    </div>
                                    <div class="min-w-0">
                                        <p class="text-sm font-semibold text-gray-900 truncate">{{ user.name }}</p>
                                    </div>
                                </div>
                            </td>
                            <td class="px-5 py-4">
                                <p class="text-sm text-gray-600 truncate">{{ user.email }}</p>
                            </td>
                            <td class="px-5 py-4">
                                <span class="inline-flex text-xs font-medium px-2.5 py-1 rounded-lg" :class="statusClass(user.status)">
                                    {{ user.status }}
                                </span>
                            </td>
                            <td class="px-5 py-4 text-center">
                                <p class="text-sm font-bold" :class="trustScoreColor(user.trustScore)">{{ user.trustScore }}</p>
                            </td>
                            <td class="px-5 py-4 text-center">
                                <p class="text-sm font-semibold text-gray-900">{{ user.orders }}</p>
                            </td>
                            <td class="px-5 py-4 text-center">
                                <p class="text-sm font-semibold"><span class="text-gray-900">{{ user.rating }}</span><span class="text-amber-400">★</span></p>
                            </td>
                            <td class="px-5 py-4">
                                <p class="text-sm text-gray-600">{{ user.province }}</p>
                            </td>
                            <td class="px-5 py-4">
                                <div class="flex items-center justify-center gap-2">
                                    <button @click="openUserModal(user)"
                                        class="p-2 rounded-lg bg-blue-50 text-blue-600 transition-colors hover:bg-blue-100"
                                        title="View Profile">
                                        <Eye class="w-4 h-4"/>
                                    </button>
                                    <button @click="() => { suspendModal.user = user; suspendModal.visible = true; suspendModal.action = 'Banned'; }"
                                        class="p-2 rounded-lg bg-red-50 text-red-600 transition-colors hover:bg-red-100"
                                        title="Ban User">
                                        <ShieldX class="w-4 h-4"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>

        <!-- ══ MODALS ════════════════════════════════════════════════════════ -->

        <!-- User Profile Screen -->
        <Transition enter-active-class="transition-all duration-300 ease-out" enter-from-class="opacity-0 translate-x-full"
            leave-active-class="transition-all duration-200 ease-in" leave-to-class="opacity-0 translate-x-full">
            <div v-if="userModal.visible" class="fixed inset-0 z-40 bg-white overflow-y-auto">
                <div v-if="userModal.user" class="min-h-screen">
                    <!-- Profile Header -->
                    <div class="bg-gradient-to-r from-blue-50 to-blue-100 border-b border-blue-200">
                        <div class="max-w-6xl mx-auto px-6 py-6">
                            <button @click="userModal.visible = false"
                                class="flex items-center gap-2 text-gray-600 hover:text-gray-900 font-medium mb-4 text-sm">
                                ← Back to Users
                            </button>
                            <div class="flex items-center justify-between gap-6">
                                <div class="flex items-center gap-4">
                                    <div class="w-20 h-20 rounded-full flex items-center justify-center text-2xl font-bold"
                                        :class="roleAvatarClass(userModal.user.role)">
                                        {{ initials(userModal.user.name) }}
                                    </div>
                                    <div>
                                        <h1 class="text-3xl font-bold text-gray-900">{{ userModal.user.name }}</h1>
                                        <p class="text-gray-600 mt-1">{{ userModal.user.email }}</p>
                                        <div class="flex items-center gap-3 mt-3">
                                            <span class="text-sm font-medium px-3 py-1 rounded-full" :class="roleClass(userModal.user.role)">{{ userModal.user.role }}</span>
                                            <span class="text-sm font-medium px-3 py-1 rounded-full" :class="statusClass(userModal.user.status)">{{ userModal.user.status }}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="text-center">
                                    <svg width="100" height="100" viewBox="0 0 64 64">
                                        <circle cx="32" cy="32" r="26" fill="none" stroke="#f3f4f6" stroke-width="6"/>
                                        <circle cx="32" cy="32" r="26" fill="none"
                                            :stroke="trustScoreBg(userModal.user.trustScore)"
                                            stroke-width="6" stroke-linecap="round"
                                            :stroke-dasharray="`${(userModal.user.trustScore/100)*163.4} 163.4`"
                                            transform="rotate(-90 32 32)"/>
                                        <text x="32" y="35" text-anchor="middle" font-size="16" font-weight="700"
                                            :fill="trustScoreBg(userModal.user.trustScore)" font-family="ui-mono,monospace">
                                            {{ userModal.user.trustScore }}
                                        </text>
                                    </svg>
                                    <p class="text-xs text-gray-600 mt-2 font-semibold">Trust Score</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Profile Content -->
                    <div class="max-w-6xl mx-auto px-6 py-8 space-y-6">
                        <!-- Basic Information -->
                        <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-6">
                            <h2 class="text-lg font-bold text-gray-900 mb-6">Basic Information</h2>
                            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">Full Name</p>
                                    <p class="text-base font-semibold text-gray-900">{{ userModal.user.name }}</p>
                                </div>
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">Email Address</p>
                                    <p class="text-base font-semibold text-gray-900">{{ userModal.user.email }}</p>
                                </div>
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">User ID</p>
                                    <p class="text-base font-mono font-semibold text-gray-900">#USR-{{ String(userModal.user.id).padStart(4,'0') }}</p>
                                </div>
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">Province</p>
                                    <p class="text-base font-semibold text-gray-900">{{ userModal.user.province }}</p>
                                </div>
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">Role</p>
                                    <span class="inline-block text-sm font-medium px-3 py-1 rounded-lg" :class="roleClass(userModal.user.role)">{{ userModal.user.role }}</span>
                                </div>
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">Status</p>
                                    <span class="inline-block text-sm font-medium px-3 py-1 rounded-lg" :class="statusClass(userModal.user.status)">{{ userModal.user.status }}</span>
                                </div>
                            </div>
                        </div>

                        <!-- Activity Stats -->
                        <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
                            <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-5">
                                <p class="text-xs text-gray-500 font-medium mb-2">Total Orders</p>
                                <p class="text-3xl font-bold text-gray-900">{{ userModal.user.orders }}</p>
                            </div>
                            <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-5">
                                <p class="text-xs text-gray-500 font-medium mb-2">Rating</p>
                                <p class="text-3xl font-bold text-amber-500">{{ userModal.user.rating }}★</p>
                            </div>
                            <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-5">
                                <p class="text-xs text-gray-500 font-medium mb-2">Disputes</p>
                                <p class="text-3xl font-bold" :class="userModal.user.disputes > 2 ? 'text-red-500' : 'text-green-600'">{{ userModal.user.disputes }}</p>
                            </div>
                            <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-5">
                                <p class="text-xs text-gray-500 font-medium mb-2">Trust Score</p>
                                <p class="text-3xl font-bold" :class="trustScoreColor(userModal.user.trustScore)">{{ userModal.user.trustScore }}</p>
                            </div>
                        </div>

                        <!-- Dates -->
                        <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-6">
                            <h2 class="text-lg font-bold text-gray-900 mb-6">Account Timeline</h2>
                            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">Joined Date</p>
                                    <p class="text-base font-semibold text-gray-900">{{ userModal.user.joinedAt }}</p>
                                </div>
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">KYC Submitted</p>
                                    <p class="text-base font-semibold text-gray-900">{{ userModal.user.submittedKycAt }}</p>
                                </div>
                                <div>
                                    <p class="text-xs font-semibold text-gray-600 mb-2">Document Score</p>
                                    <p class="text-base font-semibold" :class="userModal.user.docScore >= 100 ? 'text-green-600' : 'text-amber-600'">{{ userModal.user.docScore }}/100</p>
                                </div>
                            </div>
                        </div>

                        <!-- KYC Documents -->
                        <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-6">
                            <h2 class="text-lg font-bold text-gray-900 mb-6">KYC Document Checklist</h2>
                            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                                <div v-for="doc in userModal.user.docChecklist" :key="doc.label"
                                    class="flex items-center gap-3 p-4 border border-gray-200 rounded-xl"
                                    :class="doc.ok ? 'bg-green-50 border-green-200' : 'bg-red-50 border-red-200'">
                                    <component :is="doc.ok ? CheckCheck : X" class="w-5 h-5 flex-shrink-0"
                                        :class="doc.ok ? 'text-green-600' : 'text-red-500'"/>
                                    <p class="text-sm font-semibold" :class="doc.ok ? 'text-green-700' : 'text-red-700'">{{ doc.label }}</p>
                                </div>
                            </div>
                        </div>

                        <!-- Action Buttons -->
                        <div class="flex items-center gap-3 pb-8">
                            <button v-if="userModal.user.status === 'Pending'"
                                @click="() => { verifyUser(userModal.user.id); userModal.visible = false }"
                                class="flex items-center gap-2 px-6 py-3 rounded-xl bg-green-600 hover:bg-green-700 text-white font-medium transition">
                                <ShieldCheck class="w-5 h-5"/> Verify KYC
                            </button>
                            <button v-if="userModal.user.status === 'Pending'"
                                @click="() => { userModal.visible = false; openRejectKycModal(userModal.user) }"
                                class="flex items-center gap-2 px-6 py-3 rounded-xl bg-red-50 border border-red-200 text-red-600 hover:bg-red-100 font-medium transition">
                                <X class="w-5 h-5"/> Reject KYC
                            </button>
                            <button @click="() => { suspendModal.user = userModal.user; suspendModal.visible = true; suspendModal.action = ''; userModal.visible = false }"
                                class="flex items-center gap-2 px-6 py-3 rounded-xl bg-orange-50 border border-orange-200 text-orange-600 hover:bg-orange-100 font-medium transition">
                                <ShieldOff class="w-5 h-5"/> Suspend/Ban
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
                                <ShieldX class="w-4 h-4 text-red-500"/>
                            </div>
                            <div>
                                <p class="text-sm font-semibold text-gray-900">Reject KYC</p>
                                <p class="text-xs text-gray-400">{{ rejectKycModal.user?.name }}</p>
                            </div>
                        </div>
                        <button @click="rejectKycModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4"/>
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
                                placeholder="Additional notes for the user (optional)…"
                                class="w-full px-3 py-2 text-sm border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-400 transition placeholder:text-gray-300 resize-none"></textarea>
                        </div>
                        <div class="flex items-center gap-2 p-3 bg-amber-50 rounded-xl border border-amber-100">
                            <AlertCircle class="w-4 h-4 text-amber-500 flex-shrink-0"/>
                            <p class="text-xs text-amber-700">The user stays in Pending status and will be notified to re-submit their documents.</p>
                        </div>
                        <div class="flex gap-3">
                            <button @click="rejectKycModal.visible = false"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50 transition">Cancel</button>
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
                                <ArrowLeftRight class="w-4 h-4 text-purple-500"/>
                            </div>
                            <div>
                                <p class="text-sm font-semibold text-gray-900">Change Role</p>
                                <p class="text-xs text-gray-400">{{ roleModal.user?.name }}</p>
                            </div>
                        </div>
                        <button @click="roleModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4"/>
                        </button>
                    </div>
                    <div class="p-5 space-y-4">
                        <div class="flex items-center gap-3 p-3 bg-gray-50 rounded-xl">
                            <span class="text-xs text-gray-500">Current role:</span>
                            <span class="text-xs font-medium px-2 py-0.5 rounded-full" :class="roleClass(roleModal.user?.role)">{{ roleModal.user?.role }}</span>
                        </div>
                        <div>
                            <label class="text-xs font-semibold text-gray-700 block mb-2">Assign New Role</label>
                            <div class="space-y-2">
                                <button v-for="r in roles" :key="r"
                                    @click="roleModal.newRole = r"
                                    :disabled="r === roleModal.user?.role"
                                    :class="['w-full text-xs font-medium px-3 py-2.5 rounded-xl border transition text-left flex items-center gap-2',
                                        roleModal.newRole === r ? 'border-purple-400 bg-purple-50 text-purple-700'
                                        : r === roleModal.user?.role ? 'border-gray-100 text-gray-300 cursor-not-allowed'
                                        : 'border-gray-200 text-gray-600 hover:border-gray-300']">
                                    <span class="w-2 h-2 rounded-full flex-shrink-0"
                                        :class="r === 'Farmer' ? 'bg-green-400' : r === 'Buyer' ? 'bg-blue-400' : 'bg-purple-400'"></span>
                                    {{ r }}
                                    <span v-if="r === roleModal.user?.role" class="ml-auto text-[10px] text-gray-300">(current)</span>
                                </button>
                            </div>
                        </div>
                        <!-- Warning for Admin role -->
                        <div v-if="roleModal.newRole === 'Admin'" class="flex items-center gap-2 p-3 bg-amber-50 rounded-xl border border-amber-100">
                            <AlertCircle class="w-4 h-4 text-amber-500 flex-shrink-0"/>
                            <p class="text-xs text-amber-700">Granting Admin access gives full platform control. Confirm carefully.</p>
                        </div>
                        <div class="flex gap-3">
                            <button @click="roleModal.visible = false"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50 transition">Cancel</button>
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
                                <ShieldOff class="w-4 h-4 text-orange-500"/>
                            </div>
                            <div>
                                <p class="text-sm font-semibold text-gray-900">Suspend / Ban User</p>
                                <p class="text-xs text-gray-400">{{ suspendModal.user?.name }}</p>
                            </div>
                        </div>
                        <button @click="suspendModal.visible = false" class="w-7 h-7 rounded-lg flex items-center justify-center text-gray-400 hover:bg-gray-100">
                            <X class="w-4 h-4"/>
                        </button>
                    </div>
                    <div class="p-5 space-y-4">
                        <div class="grid grid-cols-2 gap-3">
                            <button @click="suspendModal.action = 'Suspended'"
                                :class="['p-3 rounded-xl border-2 transition text-left',
                                    suspendModal.action === 'Suspended' ? 'border-orange-400 bg-orange-50' : 'border-gray-200 hover:border-gray-300']">
                                <p class="text-xs font-semibold text-gray-800">Suspend</p>
                                <p class="text-[11px] text-gray-400 mt-0.5">Temporary · can appeal · re-activatable</p>
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
                            <button @click="suspendModal.visible = false"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50 transition">Cancel</button>
                            <button @click="executeSuspend" :disabled="!suspendModal.reason || !suspendModal.action"
                                class="flex-1 text-sm font-medium px-4 py-2.5 rounded-xl bg-orange-500 hover:bg-orange-600 text-white transition disabled:opacity-40 disabled:cursor-not-allowed">
                                Confirm Action
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </Transition>

        <!-- Toast -->
        <Transition enter-active-class="transition-all duration-300 ease-out" enter-from-class="opacity-0 translate-y-2"
            leave-active-class="transition-all duration-200 ease-in" leave-to-class="opacity-0 translate-y-2">
            <div v-if="toast.visible"
                class="fixed bottom-6 right-6 z-[60] flex items-center gap-3 px-4 py-3 rounded-2xl shadow-lg text-sm font-medium text-white min-w-[260px]"
                :class="{
                    'bg-gray-900':   toast.type === 'success',
                    'bg-red-500':    toast.type === 'error',
                    'bg-amber-500':  toast.type === 'warning',
                    'bg-purple-600': toast.type === 'info',
                }">
                <component :is="toast.type === 'success' ? CheckCheck : toast.type === 'warning' ? AlertCircle : toast.type === 'info' ? ArrowLeftRight : X"
                    class="w-4 h-4 flex-shrink-0"/>
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
    BadgeCheck, CheckCheck, AlertCircle, TrendingUp,
    UserX, ArrowLeftRight, MapPin, TrendingDown,
    Tractor, ShoppingBag, FileSearch,
} from 'lucide-vue-next'

definePageMeta({ layout: 'admin' })


const allUsers = ref([])


const users = computed(() => allUsers.value.filter(u => u.role === 'Buyer'))
const buyers = users


const roles      = ['Buyer', 'Admin']
const statusTabs = ['All', 'Pending', 'Active', 'Suspended', 'Banned']


const countByRole     = (r) => allUsers.value.filter(u => u.role === r).length
const countBuyerByStatus   = (s) => users.value.filter(u => u.status === s).length
const countByStatus   = (s) => allUsers.value.filter(u => u.status === s).length
const pendingBuyerKycCount = computed(() => users.value.filter(u => u.status === 'Pending').length)
const pendingKycCount = computed(() => allUsers.value.filter(u => u.status === 'Pending').length)
const pendingKycUsers = computed(() => users.value.filter(u => u.status === 'Pending'))
const avgBuyerTrustScore   = computed(() => Math.round(users.value.reduce((a,u) => a+u.trustScore, 0) / (users.value.length || 1)))
const avgTrustScore   = computed(() => Math.round(allUsers.value.reduce((a,u) => a+u.trustScore, 0) / (allUsers.value.length || 1)))
const trustLeaderboard = computed(() => [...users.value].sort((a,b) => b.trustScore - a.trustScore).slice(0,5))
const allTrustLeaderboard = computed(() => [...allUsers.value].sort((a,b) => b.trustScore - a.trustScore).slice(0,5))
const churnRiskUsers  = computed(() => users.value.filter(u => u.daysSince).sort((a,b) => b.daysSince - a.daysSince))
const churnCounts = computed(() => ({
    high: churnRiskUsers.value.filter(u => u.churnRisk === 'High').length,
    med:  churnRiskUsers.value.filter(u => u.churnRisk === 'Medium').length,
    low:  churnRiskUsers.value.filter(u => u.churnRisk === 'Low').length,
}))

const trustDist = computed(() => {
    const t = users.value.length
    return {
        high: Math.round(users.value.filter(u => u.trustScore >= 80).length / (t || 1) * 100),
        mid:  Math.round(users.value.filter(u => u.trustScore >= 50 && u.trustScore < 80).length / (t || 1) * 100),
        low:  Math.round(users.value.filter(u => u.trustScore < 50).length / (t || 1) * 100),
    }
})


const searchQuery  = ref('')
const filterStatus = ref('')
const filterRole   = ref('')
const filterTrust  = ref('')
const sortBy       = ref('name')

const filteredUsers = computed(() => {
    let result = users.value.filter(u => {
        const q  = searchQuery.value.toLowerCase()
        const ms = u.name.toLowerCase().includes(q) || u.email.toLowerCase().includes(q)
        const mr = !filterRole.value  || u.role   === filterRole.value
        const mv = !filterStatus.value || u.status === filterStatus.value
        const mt = !filterTrust.value ||
            (filterTrust.value === 'high' && u.trustScore >= 80) ||
            (filterTrust.value === 'mid'  && u.trustScore >= 50 && u.trustScore < 80) ||
            (filterTrust.value === 'low'  && u.trustScore < 50)
        return ms && mr && mv && mt
    })

    
    result = result.sort((a, b) => {
        switch(sortBy.value) {
            case 'name': return a.name.localeCompare(b.name)
            case 'trust-desc': return b.trustScore - a.trustScore
            case 'trust-asc': return a.trustScore - b.trustScore
            case 'orders-desc': return b.orders - a.orders
            case 'rating-desc': return b.rating - a.rating
            default: return 0
        }
    })

    return result
})

function resetFilters() {
    searchQuery.value = ''
    filterStatus.value = ''
    filterRole.value = ''
    filterTrust.value = ''
    sortBy.value = 'name'
}


const activityFilters = ['All', 'KYC', 'Role', 'Suspend']
const activityFilter  = ref('All')

const userActivity = ref([
    { id: 1, text: 'Sophea Keo verified and activated as farmer',     time: '3 min ago',  dotColor: 'bg-green-500', tag: 'KYC',     tagClass: 'bg-green-100 text-green-700'   },
    { id: 2, text: 'Dara Nou submitted KYC — farm photo missing',     time: '45 min ago', dotColor: 'bg-amber-400', tag: 'KYC',     tagClass: 'bg-amber-100 text-amber-700'   },
    { id: 3, text: 'Kosal Rith suspended — payment dispute',          time: '2 hrs ago',  dotColor: 'bg-orange-400',tag: 'Suspend', tagClass: 'bg-orange-100 text-orange-700' },
    { id: 4, text: 'Maly Rith role changed Buyer → Farmer',           time: '3 hrs ago',  dotColor: 'bg-purple-500',tag: 'Role',    tagClass: 'bg-purple-100 text-purple-700' },
    { id: 5, text: 'Bopha Chan submitted KYC, awaiting review',       time: '8 hrs ago',  dotColor: 'bg-amber-400', tag: 'KYC',     tagClass: 'bg-amber-100 text-amber-700'   },
])

const filteredActivity = computed(() =>
    activityFilter.value === 'All'
        ? userActivity.value
        : userActivity.value.filter(a => a.tag === activityFilter.value)
)

function addActivity(text, color, tag, tagClass) {
    userActivity.value.unshift({ id: Date.now(), text, time: 'Just now', dotColor: color, tag, tagClass })
    if (userActivity.value.length > 10) userActivity.value.pop()
}


const geoFilter = ref('All')
const geoData = ref([
    { province: 'Phnom Penh',   count: 412, pct: 32, farmerPct: 14 },
    { province: 'Kampong Cham', count: 198, pct: 15, farmerPct: 13 },
    { province: 'Siem Reap',    count: 156, pct: 12, farmerPct: 10 },
    { province: 'Kampot',       count: 110, pct: 9,  farmerPct: 8  },
    { province: 'Kratie',       count: 84,  pct: 7,  farmerPct: 6  },
    { province: 'Kandal',       count: 68,  pct: 5,  farmerPct: 4  },
    { province: 'Other',        count: 256, pct: 20, farmerPct: 12 },
])


const auditLog = ref([
    { id: 1, time: '05:22', user: 'Maly Rith',  from: 'Buyer',     to: 'Farmer',    by: 'admin01' },
    { id: 2, time: '06:47', user: 'Kosal Rith', from: 'Farmer',    to: 'Suspended', by: 'admin02' },
    { id: 3, time: '11:15', user: 'Bopha Lim',  from: 'Buyer',     to: 'Admin',     by: 'superadmin' },
    { id: 4, time: '14:03', user: 'Thy Heng',   from: 'Suspended', to: 'Farmer',    by: 'admin01' },
])

function addAuditLog(user, from, to) {
    auditLog.value.unshift({
        id: Date.now(),
        time: new Date().toLocaleTimeString('en-GB', { hour:'2-digit', minute:'2-digit' }),
        user, from, to, by: 'admin01',
    })
    if (auditLog.value.length > 8) auditLog.value.pop()
}


const TW = 600; const TH = 160
const trendDays = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
const trendData = { farmers: [2, 4, 3, 6, 5, 8, 4], buyers: [3, 5, 6, 4, 7, 5, 9] }
const trendMin = 0; const trendMax = 20

function trendYs(v) { return 10 + (1 - (v - trendMin) / (trendMax - trendMin)) * (TH - 20) }

function trendPts(key) {
    return trendData[key].map((v, i) => ({ x: (i / (trendData[key].length - 1)) * TW, y: trendYs(v) }))
}

function trendLinePath(key) {
    const pts = trendPts(key)
    let d = `M ${pts[0].x} ${pts[0].y}`
    for (let i = 0; i < pts.length - 1; i++) {
        const p0 = pts[Math.max(i-1,0)], p1 = pts[i], p2 = pts[i+1], p3 = pts[Math.min(i+2,pts.length-1)]
        d += ` C ${p1.x+(p2.x-p0.x)/6} ${p1.y+(p2.y-p0.y)/6}, ${p2.x-(p3.x-p1.x)/6} ${p2.y-(p3.y-p1.y)/6}, ${p2.x} ${p2.y}`
    }
    return d
}

function trendAreaPath(key) {
    const pts = trendPts(key)
    return `${trendLinePath(key)} L ${pts[pts.length-1].x} ${TH} L ${pts[0].x} ${TH} Z`
}


const moderationGoals = ref([
    { label: 'KYC Approval Rate', current: '84 verified',  target: '100 verified', percent: 84, stroke: '#22c55e' },
    { label: 'Avg Review Time',   current: 'Avg 1.8 days', target: 'Under 1 day',  percent: 55, stroke: '#3b82f6' },
])


const kycRejectionReasons = [
    'Blurry / unreadable ID', 'ID name mismatch',
    'Expired document',       'Missing land certificate',
    'Incomplete selfie',      'Duplicate account',
]


const suspendReasons = [
    'Payment dispute', 'Policy violation',
    'Spam / fake listing', 'Fraud suspected',
    'User request', 'Duplicate account',
]


const processingId = ref(null)

function verifyUser(id) {
    const u = users.value.find(x => x.id === id)
    if (!u) return
    processingId.value = id
    setTimeout(() => {
        u.status = 'Active'
        processingId.value = null
        addActivity(`${u.name} KYC verified and activated`, 'bg-green-500', 'KYC', 'bg-green-100 text-green-700')
        showToast(`${u.name} has been verified`, 'success')
    }, 400)
}

function bulkApproveKyc() {
    const pending = users.value.filter(u => u.status === 'Pending' && u.docsComplete)
    pending.forEach(u => { u.status = 'Active' })
    addActivity(`${pending.length} users bulk-verified`, 'bg-green-500', 'KYC', 'bg-green-100 text-green-700')
    showToast(`${pending.length} users verified in bulk`, 'success')
}

function reinstateUser(id) {
    const u = users.value.find(x => x.id === id)
    if (!u) return
    u.status = 'Active'
    addActivity(`${u.name} reinstated`, 'bg-green-500', 'Role', 'bg-green-100 text-green-700')
    showToast(`${u.name} has been reinstated`, 'success')
}

function nudgeUser(id) {
    const u = users.value.find(x => x.id === id)
    showToast(`Re-engagement email sent to ${u?.name}`, 'info')
}

function sendReengagement() {
    showToast('Re-engagement campaign drafted — check Messages', 'info')
}

function viewOrders() { 
    navigateTo('/admin/orders')
}
function exportCSV() { showToast('Exporting users to CSV…', 'success') }
function exportAuditLog() { showToast('Exporting audit log…', 'success') }


const rejectKycModal = ref({ visible: false, user: null, reason: '', note: '' })
function openRejectKycModal(user) { rejectKycModal.value = { visible: true, user, reason: '', note: '' } }
function executeRejectKyc() {
    const u = users.value.find(x => x.id === rejectKycModal.value.user?.id)
    if (!u) return
    addActivity(`${u.name} KYC rejected — ${rejectKycModal.value.reason}`, 'bg-red-500', 'KYC', 'bg-red-100 text-red-600')
    showToast(`KYC rejected. ${u.name} notified to re-submit.`, 'warning')
    rejectKycModal.value.visible = false
}

const roleModal = ref({ visible: false, user: null, newRole: '' })
function openRoleModal(user) { roleModal.value = { visible: true, user, newRole: '' } }
function executeRoleChange() {
    const u = users.value.find(x => x.id === roleModal.value.user?.id)
    if (!u || !roleModal.value.newRole) return
    const oldRole = u.role
    u.role = roleModal.value.newRole
    addAuditLog(u.name, oldRole, roleModal.value.newRole)
    addActivity(`${u.name} role changed ${oldRole} → ${roleModal.value.newRole}`, 'bg-purple-500', 'Role', 'bg-purple-100 text-purple-700')
    showToast(`${u.name} is now a ${roleModal.value.newRole}`, 'info')
    roleModal.value.visible = false
}

const suspendModal = ref({ visible: false, user: null, action: '', reason: '', note: '' })
function openSuspendModal(user) { suspendModal.value = { visible: true, user, action: '', reason: '', note: '' } }
function executeSuspend() {
    const u = users.value.find(x => x.id === suspendModal.value.user?.id)
    if (!u) return
    u.status = suspendModal.value.action
    addActivity(`${u.name} ${suspendModal.value.action.toLowerCase()} — ${suspendModal.value.reason}`, 'bg-orange-400', 'Suspend', 'bg-orange-100 text-orange-700')
    showToast(`${u.name} has been ${suspendModal.value.action.toLowerCase()}`, 'warning')
    suspendModal.value.visible = false
}

const userModal = ref({ visible: false, user: null })
function openUserModal(user) { userModal.value = { visible: true, user } }


const toast = ref({ visible: false, message: '', type: 'success' })
let toastTimer = null
function showToast(message, type = 'success') {
    if (toastTimer) clearTimeout(toastTimer)
    toast.value = { visible: true, message, type }
    toastTimer  = setTimeout(() => { toast.value.visible = false }, 3200)
}


const initials = (name) => name.split(' ').map(n => n[0]).join('')

const roleAvatarClass = (r) => ({
    Farmer: 'bg-green-100 text-green-700',
    Buyer:  'bg-blue-100 text-blue-700',
    Admin:  'bg-purple-100 text-purple-700',
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