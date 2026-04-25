<script setup lang="ts">
import CommonAppSidebar from '../../../../components/common/AppSidebar.vue';
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Edit Address | FarmLink Cambodia',
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
              <h1 class="text-5xl font-black text-[#154212] font-[Manrope,sans-serif] tracking-tight leading-tight">Edit Address</h1>
            </div>

            <NuxtLink to="/user/settings/address" class="inline-flex items-center justify-center gap-2 bg-white border border-[#c2c9bb]/40 text-[#154212] font-bold py-3 px-5 rounded-lg shadow-sm hover:bg-[#efeeea] transition-all active:scale-[0.98] w-fit">
              <span class="material-symbols-outlined text-sm">arrow_back</span>
              Back to Addresses
            </NuxtLink>
          </header>

          <div class="grid grid-cols-1 lg:grid-cols-12 gap-10">
            <div class="lg:col-span-7 space-y-8">
              <div class="bg-white p-8 rounded-xl shadow-sm border border-zinc-100">
                <form class="space-y-6">
                  <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div class="space-y-2">
                      <label class="text-xs font-bold uppercase tracking-wider text-[#72796e] block">Recipient Name</label>
                      <input class="w-full bg-[#e9e8e5] border-none rounded-lg p-3 text-[#1b1c1a] focus:ring-2 focus:ring-[#154212]/20 transition-all" type="text" value="Sok Samnang" />
                    </div>
                    <div class="space-y-2">
                      <label class="text-xs font-bold uppercase tracking-wider text-[#72796e] block">Phone Number</label>
                      <input class="w-full bg-[#e9e8e5] border-none rounded-lg p-3 text-[#1b1c1a] focus:ring-2 focus:ring-[#154212]/20 transition-all" type="tel" value="+855 12 345 678" />
                    </div>
                  </div>

                  <div class="space-y-2">
                    <label class="text-xs font-bold uppercase tracking-wider text-[#72796e] block">Province/City</label>
                    <select class="w-full bg-[#e9e8e5] border-none rounded-lg p-3 text-[#1b1c1a] focus:ring-2 focus:ring-[#154212]/20 transition-all appearance-none">
                      <option>Phnom Penh</option>
                      <option>Siem Reap</option>
                      <option>Battambang</option>
                      <option>Kampot</option>
                    </select>
                  </div>

                  <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div class="space-y-2">
                      <label class="text-xs font-bold uppercase tracking-wider text-[#72796e] block">District / Khan</label>
                      <input class="w-full bg-[#e9e8e5] border-none rounded-lg p-3 text-[#1b1c1a] focus:ring-2 focus:ring-[#154212]/20 transition-all" type="text" value="Chamkar Mon" />
                    </div>
                    <div class="space-y-2">
                      <label class="text-xs font-bold uppercase tracking-wider text-[#72796e] block">Sangkat / Commune</label>
                      <input class="w-full bg-[#e9e8e5] border-none rounded-lg p-3 text-[#1b1c1a] focus:ring-2 focus:ring-[#154212]/20 transition-all" type="text" value="Tonle Bassac" />
                    </div>
                  </div>

                  <div class="space-y-2">
                    <label class="text-xs font-bold uppercase tracking-wider text-[#72796e] block">Street Name & House Number</label>
                    <input class="w-full bg-[#e9e8e5] border-none rounded-lg p-3 text-[#1b1c1a] focus:ring-2 focus:ring-[#154212]/20 transition-all" type="text" value="No. 123, Street 456" />
                  </div>

                  <div class="space-y-2">
                    <label class="text-xs font-bold uppercase tracking-wider text-[#72796e] block">Delivery Instructions</label>
                    <textarea class="w-full bg-[#e9e8e5] border-none rounded-lg p-3 text-[#1b1c1a] focus:ring-2 focus:ring-[#154212]/20 transition-all resize-none" rows="3">Leave at the front desk or call before arrival.</textarea>
                  </div>
                </form>
              </div>

              <div class="flex flex-wrap items-center gap-4 pt-2">
                <button class="bg-gradient-to-br from-[#154212] to-[#2d5a27] text-white font-bold py-4 px-10 rounded-lg shadow-md hover:scale-[1.02] active:scale-95 transition-all flex items-center gap-2" type="button">
                  <span>Save Changes</span>
                  <span class="material-symbols-outlined text-sm">check_circle</span>
                </button>
                <NuxtLink class="bg-transparent border-[1.5px] border-[#154212]/20 text-[#154212] font-bold py-4 px-10 rounded-lg hover:bg-[#e9e8e5] transition-all" to="/user/settings/address">
                  Cancel
                </NuxtLink>
                <NuxtLink class="bg-[#ba1a1a] text-white font-bold py-4 px-10 rounded-lg shadow-md hover:bg-[#8f1010] transition-all" to="/user/settings/remove-address">
                  Delete Address
                </NuxtLink>
              </div>
            </div>

            <div class="lg:col-span-5 flex flex-col gap-6">
              <div class="relative group h-[500px] rounded-2xl overflow-hidden shadow-sm ring-1 ring-black/5">
                <img alt="Location Map" class="w-full h-full object-cover grayscale-[0.2] contrast-[1.1]" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBXJ33nmOY9sTin0E7hNDygM7WDWM0718Wya66IYed9FQmPCWgb7iF8g31D5JddzB274AiEIntt37JBQtStpQwm1sr377Fu2LJEwcIdwvjfdBoNyNETr0vKdLQYAgDVU1dsliJ6z4shMrRkAsLmdAQrTOIrDCgelSVKdYOC7pZ6BySncWqbzx_RKJqImgjfJJm-jVHP9M48o79uMQuVQ3ibabG97rk5yiRiypOgc3BSmghLBTv3TtSyi5CPHr5diG40UyLbOnJJB5s" />
                <div class="absolute inset-0 bg-[#154212]/5"></div>
                <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 flex flex-col items-center">
                  <div class="bg-[#563000] text-white w-10 h-10 rounded-full flex items-center justify-center shadow-lg border-2 border-white animate-bounce">
                    <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">location_on</span>
                  </div>
                  <div class="bg-[#fbf9f6] px-3 py-1 rounded-full text-[10px] font-bold uppercase tracking-tighter mt-2 shadow-sm border border-[#c2c9bb]/30">Target Location</div>
                </div>
                <div class="absolute bottom-6 left-6 right-6">
                  <div class="bg-white/90 backdrop-blur-md p-4 rounded-xl flex justify-between items-center shadow-xl">
                    <div>
                      <p class="text-[10px] font-black uppercase text-[#006e1c]">Precise Pin</p>
                      <p class="text-xs text-[#1b1c1a] font-medium">11.5564° N, 104.9282° E</p>
                    </div>
                    <button class="bg-[#154212] text-white text-[10px] font-bold py-2 px-4 rounded-full uppercase tracking-widest hover:bg-[#006e1c] transition-colors" type="button">
                      ADJUST
                    </button>
                  </div>
                </div>
              </div>

              <div class="bg-[#91f78e]/30 border border-[#91f78e] p-6 rounded-xl flex items-start gap-4">
                <div class="bg-[#91f78e] p-2 rounded-full">
                  <span class="material-symbols-outlined text-[#005313]">verified</span>
                </div>
                <div>
                  <h4 class="text-[#005313] font-bold text-sm uppercase tracking-tight">Verified Logistics Zone</h4>
                  <p class="text-[#005313]/80 text-xs mt-1 leading-relaxed">This address is within our Premium Fresh delivery zone. Typical farm-to-door time is under 12 hours.</p>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>

    <footer class="bg-[#efeeea] py-12 px-8 border-t border-[#c2c9bb]/10">
      <div class="max-w-7xl mx-auto flex flex-col md:flex-row justify-between items-start md:items-center gap-8">
        <div class="space-y-4">
          <span class="text-xl font-black text-[#154212] uppercase tracking-widest">FarmLink</span>
          <p class="text-sm text-[#42493e] max-w-xs">Cultivating direct connections between Cambodian agrarian heritage and modern culinary standards.</p>
        </div>
        <div class="flex gap-12">
          <div class="space-y-3">
            <p class="text-xs font-bold uppercase tracking-widest text-[#154212]">Support</p>
            <ul class="text-sm text-[#42493e] space-y-2">
              <li><a class="hover:text-[#006e1c] transition-colors" href="#">Help Center</a></li>
              <li><a class="hover:text-[#006e1c] transition-colors" href="#">Delivery FAQ</a></li>
            </ul>
          </div>
          <div class="space-y-3">
            <p class="text-xs font-bold uppercase tracking-widest text-[#154212]">Connect</p>
            <ul class="text-sm text-[#42493e] space-y-2">
              <li><a class="hover:text-[#006e1c] transition-colors" href="#">Instagram</a></li>
              <li><a class="hover:text-[#006e1c] transition-colors" href="#">Facebook</a></li>
            </ul>
          </div>
        </div>
        <div class="bg-white p-6 rounded-xl shadow-sm border border-[#c2c9bb]/20">
          <p class="text-xs font-bold text-[#563000] uppercase mb-2">Need Harvest Help?</p>
          <p class="text-sm font-bold text-[#154212]">+855 23 987 654</p>
        </div>
      </div>
      <div class="max-w-7xl mx-auto mt-12 pt-8 border-t border-[#c2c9bb]/10 flex justify-between items-center text-[10px] font-medium text-[#72796e] uppercase tracking-[0.2em]">
        <span>© 2024 FarmLink Cambodia. All Rights Reserved.</span>
        <div class="flex gap-6">
          <a class="hover:text-[#154212] transition-colors" href="#">Privacy</a>
          <a class="hover:text-[#154212] transition-colors" href="#">Terms</a>
        </div>
      </div>
    </footer>
  </div>
</template>
