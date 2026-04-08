<script setup lang="ts">
import CommonAppSidebar from '../../../../components/common/AppSidebar.vue';
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Add New Address | FarmLink Cambodia',
  link: [
    {
      rel: 'stylesheet',
      href: 'https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&family=Inter:wght@400;500;600&display=swap',
    },
    {
      rel: 'stylesheet',
      href: 'https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap',
    },
  ],
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

        <section class="flex-1 space-y-12">
          <header>
            <span class="text-[#006e1c] font-bold text-[10px] tracking-[0.25em] uppercase mb-2 block">Secure Delivery</span>
            <h1 class="text-5xl font-black text-[#154212] font-[Manrope,sans-serif] tracking-tight leading-tight">Add New Address</h1>
            <div class="mt-4 flex items-center gap-2">
              <span class="w-8 h-[2px] bg-[#563000]"></span>
              <p class="text-[#42493e] font-medium">Ensure your fresh harvest reaches you exactly where you are. Pin your location for our local delivery partners.</p>
            </div>
          </header>

          <div class="grid grid-cols-1 lg:grid-cols-12 gap-12">
            <div class="lg:col-span-7">
              <div class="bg-white p-8 rounded-xl shadow-sm border border-zinc-100">
                <form class="space-y-8">
                  <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div class="space-y-2">
                      <label class="block font-[Inter,sans-serif] text-[11px] font-semibold uppercase tracking-wider text-[#72796e] px-1">Recipient Name</label>
                      <input class="w-full bg-[#efeeea] border-none rounded-lg px-4 py-3 focus:ring-2 focus:ring-[#006e1c]/20 transition-shadow text-[#1b1c1a]" placeholder="e.g. Sophal Meas" type="text" />
                    </div>
                    <div class="space-y-2">
                      <label class="block font-[Inter,sans-serif] text-[11px] font-semibold uppercase tracking-wider text-[#72796e] px-1">Phone Number</label>
                      <input class="w-full bg-[#efeeea] border-none rounded-lg px-4 py-3 focus:ring-2 focus:ring-[#006e1c]/20 transition-shadow text-[#1b1c1a]" placeholder="+855 000 000 000" type="tel" />
                    </div>
                  </div>

                  <div class="space-y-2">
                    <label class="block font-[Inter,sans-serif] text-[11px] font-semibold uppercase tracking-wider text-[#72796e] px-1">Province / City</label>
                    <div class="relative">
                      <select class="w-full appearance-none bg-[#efeeea] border-none rounded-lg px-4 py-3 focus:ring-2 focus:ring-[#006e1c]/20 text-[#1b1c1a] pr-10">
                        <option>Phnom Penh</option>
                        <option>Siem Reap</option>
                        <option>Battambang</option>
                        <option>Kampot</option>
                      </select>
                      <span class="material-symbols-outlined absolute right-3 top-3 pointer-events-none text-[#72796e]">expand_more</span>
                    </div>
                  </div>

                  <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div class="space-y-2">
                      <label class="block font-[Inter,sans-serif] text-[11px] font-semibold uppercase tracking-wider text-[#72796e] px-1">District</label>
                      <input class="w-full bg-[#efeeea] border-none rounded-lg px-4 py-3 focus:ring-2 focus:ring-[#006e1c]/20 transition-shadow text-[#1b1c1a]" placeholder="e.g. Daun Penh" type="text" />
                    </div>
                    <div class="space-y-2">
                      <label class="block font-[Inter,sans-serif] text-[11px] font-semibold uppercase tracking-wider text-[#72796e] px-1">Sangkat / Commune</label>
                      <input class="w-full bg-[#efeeea] border-none rounded-lg px-4 py-3 focus:ring-2 focus:ring-[#006e1c]/20 transition-shadow text-[#1b1c1a]" placeholder="e.g. Phsar Thmei" type="text" />
                    </div>
                  </div>

                  <div class="space-y-2">
                    <label class="block font-[Inter,sans-serif] text-[11px] font-semibold uppercase tracking-wider text-[#72796e] px-1">Street Name & House Number</label>
                    <input class="w-full bg-[#efeeea] border-none rounded-lg px-4 py-3 focus:ring-2 focus:ring-[#006e1c]/20 transition-shadow text-[#1b1c1a]" placeholder="House 123, St 51 (Pasteur)" type="text" />
                  </div>

                  <div class="space-y-2">
                    <label class="block font-[Inter,sans-serif] text-[11px] font-semibold uppercase tracking-wider text-[#72796e] px-1">Delivery Instructions (Optional)</label>
                    <textarea class="w-full bg-[#efeeea] border-none rounded-lg px-4 py-3 focus:ring-2 focus:ring-[#006e1c]/20 transition-shadow text-[#1b1c1a] resize-none" placeholder="e.g. Leave at the gate, building with the green roof..." rows="3"></textarea>
                  </div>

                  <div class="flex items-center gap-4 pt-4">
                    <button class="bg-gradient-to-br from-[#154212] to-[#2d5a27] text-white px-10 py-4 rounded-lg font-[Manrope,sans-serif] font-bold text-lg shadow-lg shadow-[#154212]/10 hover:shadow-[#154212]/20 transition-all active:scale-[0.98]" type="submit">
                      Save Address
                    </button>
                    <NuxtLink class="px-8 py-4 rounded-lg font-[Manrope,sans-serif] font-bold text-[#154212] hover:bg-[#e9e8e5] transition-colors" to="/user/settings/address">
                      Cancel
                    </NuxtLink>
                  </div>
                </form>
              </div>
            </div>

            <div class="lg:col-span-5 space-y-8">
              <div class="bg-[#f5f3f0] p-2 rounded-xl border border-[#c2c9bb]/15 overflow-hidden shadow-sm">
                <div class="relative aspect-square rounded-lg overflow-hidden group">
                  <img class="w-full h-full object-cover" alt="Location map" src="https://lh3.googleusercontent.com/aida-public/AB6AXuA43AbUoqI0aNItlfW-395k_Eqfj1TsgJIpCio0FojnavgJo_uvfJpxfA6KZ4wZZayA5Up2O5F9VDZuW5mDcIzyQA8m8vx8EeKoXIB6DDff41vBwIr2ODLQCOTHHa_VGwD4SYOgKDj48ozQNpVlWjKIjyyzb9ihehYq7arLa4WsYF9QE8rwJ0OXPpl-Z5DvTqoqHRa1G-6sy2g-iwOoKVvr4xvnzdBt_Iq4-2zoCuEULj-VQTifseRVDFRZqVQvVu0UqrMN1OS9GXQ" />
                  <div class="absolute inset-0 bg-[#154212]/5 group-hover:bg-transparent transition-colors"></div>
                  <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2">
                    <span class="material-symbols-outlined text-[#006e1c] text-5xl" style="font-variation-settings: 'FILL' 1;">location_on</span>
                  </div>
                  <div class="absolute bottom-4 left-4 right-4 bg-white/90 backdrop-blur-md p-3 rounded-lg flex items-center justify-between">
                    <span class="text-xs font-bold text-[#154212]">Confirm location marker</span>
                    <button class="text-[#006e1c] text-xs font-black uppercase tracking-widest" type="button">Adjust</button>
                  </div>
                </div>
              </div>

              <div class="bg-[#774400] text-[#ffb870] p-8 rounded-xl relative overflow-hidden">
                <div class="relative z-10">
                  <h3 class="font-[Manrope,sans-serif] text-xl font-extrabold mb-3">Harvest Trust</h3>
                  <p class="text-sm leading-relaxed opacity-90">Our logistics network uses specialized cool-chain transport to ensure your Battambang oranges and Kampot peppers arrive with field-fresh crispness.</p>
                  <div class="mt-6 flex items-center gap-3">
                    <span class="material-symbols-outlined text-[#ffdcbe]">verified_user</span>
                    <span class="text-xs font-bold uppercase tracking-widest">100% Secure Delivery</span>
                  </div>
                </div>
                <div class="absolute -bottom-4 -right-4 opacity-10">
                  <span class="material-symbols-outlined text-[120px]">local_shipping</span>
                </div>
              </div>

              <div class="flex flex-wrap gap-2">
                <span class="bg-[#91f78e] text-[#00731e] px-4 py-1.5 rounded-full text-xs font-bold tracking-wide uppercase">Primary Hub: Phnom Penh</span>
                <span class="bg-[#e3e2df] text-[#42493e] px-4 py-1.5 rounded-full text-xs font-bold tracking-wide uppercase">Rural Express</span>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>

    <footer class="bg-[#efeeea] py-16 px-8 border-t border-[#c2c9bb]/10">
      <div class="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-12">
        <div>
          <span class="font-[Manrope,sans-serif] font-black text-[#154212] text-xl uppercase tracking-widest">FarmLink</span>
          <p class="mt-4 text-sm text-[#42493e] leading-relaxed">Connecting Cambodia's finest growers with conscious urban consumers. Pure origin, fair trade, delivered daily.</p>
        </div>
        <div>
          <h4 class="font-[Manrope,sans-serif] font-bold text-[#154212] mb-4">Support</h4>
          <ul class="space-y-2 text-sm text-[#42493e]">
            <li><a class="hover:text-[#006e1c] transition-colors" href="#">Delivery FAQs</a></li>
            <li><a class="hover:text-[#006e1c] transition-colors" href="#">Return Policy</a></li>
            <li><a class="hover:text-[#006e1c] transition-colors" href="#">Farmer's Guild</a></li>
          </ul>
        </div>
        <div>
          <h4 class="font-[Manrope,sans-serif] font-bold text-[#154212] mb-4">Community</h4>
          <div class="flex gap-4">
            <span class="material-symbols-outlined text-[#154212] cursor-pointer hover:text-[#006e1c]">social_leaderboard</span>
            <span class="material-symbols-outlined text-[#154212] cursor-pointer hover:text-[#006e1c]">photo_camera</span>
            <span class="material-symbols-outlined text-[#154212] cursor-pointer hover:text-[#006e1c]">alternate_email</span>
          </div>
        </div>
      </div>

      <div class="max-w-7xl mx-auto mt-12 pt-8 border-t border-[#c2c9bb]/30 flex justify-between items-center text-xs font-medium text-[#72796e]">
        <span>© 2024 FarmLink Cambodia. All rights reserved.</span>
        <div class="flex gap-6">
          <a href="#">Privacy Policy</a>
          <a href="#">Terms of Harvest</a>
        </div>
      </div>
    </footer>
  </div>
</template>
