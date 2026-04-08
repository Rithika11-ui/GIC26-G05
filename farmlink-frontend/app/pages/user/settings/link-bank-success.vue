<script setup lang="ts">
import CommonAppSidebar from '../../../../components/common/AppSidebar.vue';
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Bank Linked Successfully | FarmLink Cambodia',
  link: [
    {
      rel: 'stylesheet',
      href: 'https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;700;800&family=Inter:wght@400;500;600&display=swap',
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
        <CommonAppSidebar active="payment" />

        <section class="flex-1 space-y-12">
          <div class="grid grid-cols-1 lg:grid-cols-12 gap-6 items-stretch">
            <div class="lg:col-span-7 bg-[#f5f3f0] rounded-2xl p-8 sm:p-10 relative overflow-hidden">
              <div class="absolute -top-10 -right-10 w-40 h-40 bg-[#006e1c]/5 rounded-[60%_40%_30%_70%_/_60%_30%_70%_40%]"></div>
              <div class="relative">
                <div class="w-20 h-20 bg-[#006e1c] rounded-full flex items-center justify-center mb-6 shadow-xl shadow-[#006e1c]/20">
                  <span class="material-symbols-outlined text-white text-5xl" style="font-variation-settings: 'FILL' 1;">check_circle</span>
                </div>
                <span class="text-xs font-bold uppercase tracking-widest text-[#006e1c] mb-2 block">Transaction Confirmed</span>
                <h1 class="font-[Manrope,sans-serif] text-4xl sm:text-5xl font-extrabold text-[#154212] leading-tight mb-4">Bank Account Linked Successfully</h1>
                <p class="text-[#42493e] leading-relaxed max-w-md">Your primary funding source has been securely verified. You can now use this account for instant farm-to-table transactions and seasonal harvest subscriptions.</p>
              </div>

              <div class="flex flex-wrap gap-4 pt-8">
                <NuxtLink to="/user/settings/payment" class="px-8 py-3.5 bg-gradient-to-br from-[#154212] to-[#2d5a27] text-white rounded-lg font-[Manrope,sans-serif] font-bold text-sm flex items-center gap-2 hover:shadow-lg transition-all active:scale-95">
                  <span>Back to Payments</span>
                  <span class="material-symbols-outlined text-sm">arrow_forward</span>
                </NuxtLink>
                <button class="px-8 py-3.5 border-[1.5px] border-[#154212]/20 text-[#154212] rounded-lg font-[Manrope,sans-serif] font-bold text-sm hover:bg-[#efeeea] transition-all" type="button">
                  View Statements
                </button>
              </div>
            </div>

            <div class="lg:col-span-5 grid grid-rows-2 gap-6">
              <div class="bg-white border border-[#c2c9bb]/20 p-7 rounded-2xl flex flex-col justify-center">
                <h3 class="text-[10px] font-bold uppercase tracking-[0.15em] text-[#72796e] mb-6">Secured Account</h3>
                <div class="flex items-center gap-4">
                  <div class="w-12 h-12 bg-[#563000]/10 rounded-lg flex items-center justify-center">
                    <span class="material-symbols-outlined text-[#563000]">account_balance</span>
                  </div>
                  <div>
                    <p class="font-bold text-[#154212]">AgriBank Platinum</p>
                    <p class="text-xs text-[#42493e]">Checking •••• 8829</p>
                  </div>
                </div>
              </div>

              <div class="rounded-2xl overflow-hidden relative group">
                <img
                  alt="Lush green fields"
                  class="w-full h-full object-cover grayscale-[20%] group-hover:scale-110 transition-transform duration-700"
                  src="https://lh3.googleusercontent.com/aida-public/AB6AXuCKbxdfc0qai-uP7ashRtVCe7vRjS1F-dLenp76NZypOaeEf32OmUiuX107Z6esbPTUnIj4bImbxzzgi-kLH8j79BrHceiz5dKEX8ki17ehHQ3boo0kDQ_e3B5i--YOFPfZWvwnzgJJHi7GlyFKhrvDWrJSU2Ez-GYQDcqGI00xtnaKj1ccACjmu9tsNYjnUCgia1VwxBqPYDt8MtfvDkWTdhnmkkhpBO9nr7IIwmuZAogQD8ZMSWNjEoX3eaFXMJu3CVa5X6nFR70"
                />
                <div class="absolute inset-0 bg-[#154212]/20 mix-blend-multiply"></div>
                <div class="absolute bottom-4 left-4 right-4 p-4 bg-white/10 backdrop-blur rounded-lg border border-white/20">
                  <p class="text-[10px] text-white/90 uppercase tracking-widest font-bold">The Grower's Trust</p>
                  <p class="text-xs text-white/80 italic">Supporting local soil since 1994.</p>
                </div>
              </div>
            </div>
          </div>

          <section>
            <div class="flex items-center justify-between mb-6">
              <h2 class="font-[Manrope,sans-serif] text-xl font-bold text-[#154212]">Explore Your Ecosystem</h2>
              <div class="h-px flex-grow mx-6 bg-[#c2c9bb]/40"></div>
            </div>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
              <div class="bg-[#efeeea] p-6 rounded-xl hover:bg-[#e9e8e5] transition-colors cursor-pointer group">
                <span class="material-symbols-outlined text-[#006e1c] mb-4 group-hover:scale-110 transition-transform block">eco</span>
                <h4 class="font-bold text-[#154212] mb-2">Setup Subscriptions</h4>
                <p class="text-xs text-[#42493e]">Automate your weekly organic veggie basket delivery.</p>
              </div>
              <div class="bg-[#efeeea] p-6 rounded-xl hover:bg-[#e9e8e5] transition-colors cursor-pointer group">
                <span class="material-symbols-outlined text-[#563000] mb-4 group-hover:scale-110 transition-transform block">assignment_ind</span>
                <h4 class="font-bold text-[#154212] mb-2">Farmer Profiles</h4>
                <p class="text-xs text-[#42493e]">Meet the growers behind your recent purchases.</p>
              </div>
              <div class="bg-[#efeeea] p-6 rounded-xl hover:bg-[#e9e8e5] transition-colors cursor-pointer group">
                <span class="material-symbols-outlined text-[#154212] mb-4 group-hover:scale-110 transition-transform block">verified_user</span>
                <h4 class="font-bold text-[#154212] mb-2">Security Hub</h4>
                <p class="text-xs text-[#42493e]">Manage your biometrics and transaction limits.</p>
              </div>
            </div>
          </section>
        </section>
      </div>
    </main>

    <footer class="w-full py-12 px-8 grid grid-cols-1 md:grid-cols-2 gap-8 border-t border-[#efeeea] bg-[#efeeea] mt-auto">
      <div class="space-y-6">
        <span class="text-lg font-black text-[#154212] font-[Manrope,sans-serif] uppercase tracking-tight">Farm Link</span>
        <p class="text-[#5a5b58] text-sm max-w-sm leading-relaxed">Empowering the soil-keepers of Cambodia through transparent commerce and direct-to-consumer sustainability.</p>
        <p class="text-xs text-[#5a5b58] opacity-75">© 2024 FarmLink Cambodia. Sourced from the soil of Cambodia.</p>
      </div>

      <div class="grid grid-cols-2 sm:grid-cols-3 gap-8">
        <div class="space-y-4">
          <h4 class="font-bold text-[#154212] text-xs uppercase tracking-widest">Platform</h4>
          <nav class="flex flex-col gap-2">
            <a class="text-sm text-[#5a5b58] hover:text-[#006E1C] transition-colors" href="#">About Us</a>
            <a class="text-sm text-[#5a5b58] hover:text-[#006E1C] transition-colors" href="#">Sustainability</a>
          </nav>
        </div>
        <div class="space-y-4">
          <h4 class="font-bold text-[#154212] text-xs uppercase tracking-widest">Support</h4>
          <nav class="flex flex-col gap-2">
            <a class="text-sm text-[#5a5b58] hover:text-[#006E1C] transition-colors" href="#">Shipping Policy</a>
            <a class="text-sm text-[#5a5b58] hover:text-[#006E1C] transition-colors" href="#">Terms of Service</a>
          </nav>
        </div>
        <div class="space-y-4 col-span-2 sm:col-span-1">
          <h4 class="font-bold text-[#154212] text-xs uppercase tracking-widest">Contact</h4>
          <a class="text-sm text-[#5a5b58] hover:text-[#006E1C] transition-colors" href="#">Contact Support</a>
          <div class="flex gap-4 mt-4">
            <span class="material-symbols-outlined text-[#154212] cursor-pointer">language</span>
            <span class="material-symbols-outlined text-[#154212] cursor-pointer">hub</span>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>

