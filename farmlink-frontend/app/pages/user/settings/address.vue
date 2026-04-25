<script setup lang="ts">
import CommonAppSidebar from '../../../../components/common/AppSidebar.vue';
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Saved Addresses | FarmLink Cambodia',
});
</script>

<template>
  <div class="min-h-screen flex flex-col bg-[#fbf9f6] text-zinc-900">
    <nav class="fixed top-0 z-50 w-full bg-[#f5f1e6] border-b border-zinc-200/70">
      <div class="max-w-[1440px] mx-auto px-4 sm:px-6 py-3 flex items-center justify-between gap-4">
        <div class="flex items-center gap-3">
          <img
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuDxOVR_zyChQjJtpsz7uv6I95KZvXXMjdfhytppn0wii18iFOb85phtM6Me-Wq3F8rDH7KgRDh9ogSDlko0dxBiPcN3qVrhoLFXTG5GuYaVt05jNPcxWW12g7R9SnCr59Li2JIk2040vUdxOGHqHRlCeTc0WFN5koj8Y1cyRBd25JUuv7eamJG-EMr7Z4T4c3Hp9NtjqSvBoyma4cWZJxxQSKSZRKvtn56kwAmf27B7nViVqPc5vC2557SJdMeVhgzwu25O1U1P1pQ"
            alt="FarmLink logo"
            class="h-10"
          />
          <div class="hidden sm:block">
            <p class="text-sm font-black text-[#154212] leading-tight tracking-tight uppercase">Farm Link</p>
            <p class="text-[9px] text-zinc-500 font-bold uppercase tracking-[0.12em]">Farm & Agri-Business Services</p>
          </div>
        </div>

        <div class="hidden lg:flex items-center gap-6">
          <a href="#" class="bg-[#e9c46a] text-black font-extrabold text-[11px] tracking-wider px-4 py-1.5 rounded-full border border-black shadow-[2px_2px_0_0_rgba(0,0,0,1)] uppercase">Home</a>
          <a href="#" class="text-[11px] font-bold tracking-wider uppercase">Services</a>
          <a href="#" class="text-[11px] font-bold tracking-wider uppercase">Pages</a>
          <a href="#" class="text-[11px] font-bold tracking-wider uppercase">Shop</a>
          <a href="#" class="text-[11px] font-bold tracking-wider uppercase">Contacts</a>
        </div>

        <div class="flex items-center gap-3 sm:gap-4">
          <button class="hidden md:inline-flex text-zinc-800">
            <span class="material-symbols-outlined">bookmark</span>
          </button>
          <button class="hidden md:inline-flex text-zinc-800">
            <span class="material-symbols-outlined">shopping_cart</span>
          </button>
          <button class="bg-[#e9c46a] px-4 sm:px-5 py-2 rounded-full border border-black text-[11px] font-extrabold uppercase tracking-wider shadow-[2px_2px_0_0_rgba(0,0,0,1)]">
            Shop Now
          </button>
          <button class="bg-[#e9c46a] p-1.5 rounded-full border border-black shadow-[1px_1px_0_0_rgba(0,0,0,1)]">
            <span class="material-symbols-outlined text-black">person</span>
          </button>
        </div>
      </div>
    </nav>

    <main class="max-w-7xl mx-auto w-full pt-28 sm:pt-32 pb-16 px-4 sm:px-6 lg:px-8 flex-1">
      <div class="flex flex-col md:flex-row gap-8 md:gap-10">
        <CommonAppSidebar active="address" />

        <section class="flex-1 space-y-10">
          <header class="flex flex-col gap-4 sm:flex-row sm:items-end sm:justify-between">
            <div>
              <span class="text-[#006e1c] font-bold text-[10px] tracking-[0.25em] uppercase mb-2 block">Shipping Destinations</span>
              <h1 class="text-5xl font-black text-[#154212] font-[Manrope,sans-serif] tracking-tight leading-tight">Saved Addresses</h1>
            </div>

            <NuxtLink to="/user/settings/add-address" class="inline-flex items-center justify-center gap-2 bg-gradient-to-br from-[#154212] to-[#2d5a27] text-white font-bold py-3 px-5 rounded-lg shadow-md hover:shadow-lg transition-all active:scale-[0.98] w-fit">
              <span class="material-symbols-outlined text-sm">add_location_alt</span>
              Add New Address
            </NuxtLink>
          </header>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div class="group relative bg-white rounded-xl p-8 border border-[#c2c9bb]/10 shadow-sm hover:shadow-lg transition-all duration-300">
              <div class="absolute top-6 right-6 flex gap-2">
                <span class="bg-[#91f78e] text-[#00731e] text-[10px] font-bold uppercase tracking-widest px-3 py-1 rounded-full">Default</span>
              </div>
              <div class="mb-6">
                <span class="material-symbols-outlined text-[#154212] text-3xl mb-4 block" style="font-variation-settings: 'FILL' 1;">home</span>
                <h3 class="font-[Manrope,sans-serif] text-xl font-bold text-[#154212] mb-1">Home</h3>
                <p class="text-sm text-[#42493e] font-medium">Sok Samnang</p>
              </div>
              <div class="space-y-2 mb-8">
                <p class="text-sm text-[#1b1c1a] leading-relaxed">
                  No. 123, Street 456, Sangkat Boeng Keng Kang I,<br />
                  Khan Chamkarmon, Phnom Penh 120102
                </p>
                <p class="text-sm text-[#1b1c1a] flex items-center gap-2">
                  <span class="material-symbols-outlined text-sm">phone</span>
                  +855 12 345 678
                </p>
              </div>
              <div class="flex items-center gap-4 border-t border-[#efeeea] pt-6">
                <NuxtLink to="/user/settings/edit-address" class="text-[#154212] font-[Inter,sans-serif] text-[11px] font-bold uppercase tracking-wider flex items-center gap-1 hover:underline">
                  <span class="material-symbols-outlined text-base">edit</span>
                  Edit
                </NuxtLink>
                <NuxtLink to="/user/settings/remove-address" class="text-[#ba1a1a] font-[Inter,sans-serif] text-[11px] font-bold uppercase tracking-wider flex items-center gap-1 hover:underline">
                  <span class="material-symbols-outlined text-base">delete</span>
                  Delete
                </NuxtLink>
              </div>
            </div>

            <div class="group relative bg-white rounded-xl p-8 border border-[#c2c9bb]/10 shadow-sm hover:shadow-lg transition-all duration-300">
              <div class="mb-6">
                <span class="material-symbols-outlined text-[#154212] text-3xl mb-4 block" style="font-variation-settings: 'FILL' 1;">work</span>
                <h3 class="font-[Manrope,sans-serif] text-xl font-bold text-[#154212] mb-1">Work</h3>
                <p class="text-sm text-[#42493e] font-medium">Sok Samnang (Harvest Trust HQ)</p>
              </div>
              <div class="space-y-2 mb-8">
                <p class="text-sm text-[#1b1c1a] leading-relaxed">
                  Exchange Square, Level 15, Street 106,<br />
                  Wat Phnom, Daun Penh, Phnom Penh 120211
                </p>
                <p class="text-sm text-[#1b1c1a] flex items-center gap-2">
                  <span class="material-symbols-outlined text-sm">phone</span>
                  +855 98 765 432
                </p>
              </div>
              <div class="flex items-center gap-4 border-t border-[#efeeea] pt-6">
                <NuxtLink to="/user/settings/edit-address" class="text-[#154212] font-[Inter,sans-serif] text-[11px] font-bold uppercase tracking-wider flex items-center gap-1 hover:underline">
                  <span class="material-symbols-outlined text-base">edit</span>
                  Edit
                </NuxtLink>
                <NuxtLink to="/user/settings/remove-address" class="text-[#ba1a1a] font-[Inter,sans-serif] text-[11px] font-bold uppercase tracking-wider flex items-center gap-1 hover:underline">
                  <span class="material-symbols-outlined text-base">delete</span>
                  Delete
                </NuxtLink>
              </div>
            </div>

            <NuxtLink to="/user/settings/add-address" class="group bg-[#efeeea]/30 border-2 border-dashed border-[#c2c9bb]/30 rounded-xl p-8 flex flex-col items-center justify-center gap-4 hover:border-[#154212]/50 hover:bg-[#efeeea]/50 transition-all duration-300 h-full min-h-[300px]">
              <div class="w-16 h-16 rounded-full bg-[#fbf9f6] flex items-center justify-center text-[#2d5a27] group-hover:scale-110 transition-transform shadow-sm">
                <span class="material-symbols-outlined text-3xl">add_circle</span>
              </div>
              <div class="text-center">
                <span class="font-[Manrope,sans-serif] text-lg font-bold text-[#154212] block">Add New Address</span>
                <span class="text-sm text-[#42493e]">Ship to a new location</span>
              </div>
            </NuxtLink>

            <div class="relative overflow-hidden rounded-xl bg-[#154212] text-white p-8 flex flex-col justify-between min-h-[300px]">
              <img class="absolute inset-0 w-full h-full object-cover mix-blend-overlay opacity-30" alt="kampot pepper vines" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBJUe0cwXW1X89YTwcqSZwPHmQ5yhOWbHrU3jCBaTnXIami2lZklE4uKr_zgU-GMjB-JPlypyhHT7b9XgW-ojNNSLEc_VxxdO7M5h_-4-XMXR79mv5lW7Zy0J9QFs7R93vgqF_Th6aaw7l-IF3BNN8moiPgmJ9CBqHODiJKhZ_CpEpQ84LD_AdiIKnThjHCblrsgcavQB6YJ91F7jfSxY7La1fX0XKOCZyAoBMbdzO5v0SedMv0IU9Pb0Bf2RbUlpcHEVNhUyZ4M4k" />
              <div class="relative z-10">
                <span class="bg-[#563000] text-white text-[10px] font-bold uppercase tracking-[0.2em] px-3 py-1 rounded-full mb-4 inline-block">Sustainability Note</span>
                <h4 class="font-[Manrope,sans-serif] text-2xl font-bold mb-4 italic leading-tight">Farmer-to-Fork Efficiency</h4>
                <p class="text-sm text-white/80 leading-relaxed mb-6">
                  Accurate addresses help our local couriers reduce carbon emissions by optimizing delivery routes across the Kingdom.
                </p>
              </div>
              <div class="relative z-10 text-xs font-bold tracking-widest text-[#91f78e]">
                CULTIVATING FUTURE © 2024
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>

    <footer class="w-full rounded-t-[2rem] mt-20 bg-[#154212]">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-8 px-12 py-16 max-w-[1440px] mx-auto">
        <div class="space-y-6">
          <div class="text-lg font-bold text-white font-[Manrope,sans-serif] italic">Harvest Trust</div>
          <p class="text-white/70 text-sm max-w-md leading-relaxed">
            Connecting Cambodia's heritage farmers directly to your table. We celebrate transparency, ethics, and the art of sustainable agriculture.
          </p>
          <div class="text-white/50 text-xs font-medium tracking-wide">
            © 2024 Harvest Trust. Cultivating a sustainable future for Cambodia.
          </div>
        </div>

        <div class="flex flex-col md:items-end justify-between gap-8">
          <nav class="flex flex-wrap md:justify-end gap-x-8 gap-y-4">
            <a class="text-white/70 hover:text-white transition-colors text-xs font-medium tracking-wide" href="#">Sustainability Report</a>
            <a class="text-white/70 hover:text-white transition-colors text-xs font-medium tracking-wide" href="#">Farmer Ethics</a>
            <a class="text-white/70 hover:text-white transition-colors text-xs font-medium tracking-wide" href="#">Shipping Policy</a>
            <a class="text-white/70 hover:text-white transition-colors text-xs font-medium tracking-wide" href="#">Privacy</a>
            <a class="text-white/70 hover:text-white transition-colors text-xs font-medium tracking-wide" href="#">Terms</a>
          </nav>
          <div class="flex gap-6">
            <span class="material-symbols-outlined text-white/40 cursor-pointer hover:text-white transition-colors">social_leaderboard</span>
            <span class="material-symbols-outlined text-white/40 cursor-pointer hover:text-white transition-colors">camera</span>
            <span class="material-symbols-outlined text-white/40 cursor-pointer hover:text-white transition-colors">agriculture</span>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>
