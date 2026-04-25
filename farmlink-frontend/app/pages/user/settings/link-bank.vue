<script setup lang="ts">
import CommonAppSidebar from '../../../components/common/AppSidebar.vue';
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Link Bank Account | FarmLink Cambodia',
});

const goToSuccess = () => {
  navigateTo('/user/settings/link-bank-success');
};
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

        <section class="flex-1 space-y-10">
          <header>
            <span class="font-bold uppercase tracking-[0.15em] text-[#006e1c] text-[11px] mb-2 block">Financing Your Harvest</span>
            <h1 class="font-[Manrope,sans-serif] text-5xl font-extrabold text-[#154212] tracking-tight">Link Bank Account</h1>
            <p class="mt-4 text-[#42493e] text-lg max-w-xl leading-relaxed">Securely connect your Cambodian bank account to streamline your farm-to-table transactions and support local growers directly.</p>
          </header>

          <div class="grid grid-cols-1 md:grid-cols-[minmax(0,1fr)_300px] lg:grid-cols-[minmax(0,1fr)_320px] gap-8 items-start">
            <div class="min-w-0 space-y-8">
              <form class="space-y-6" @submit.prevent="goToSuccess">
                <div class="space-y-2">
                  <label class="text-sm font-bold text-[#42493e] ml-1">Bank Name</label>
                  <div class="relative">
                    <select class="w-full bg-[#e9e8e5] border-none rounded-lg py-4 px-4 appearance-none focus:ring-2 focus:ring-[#154212]/20 text-[#1b1c1a] font-medium">
                      <option disabled selected value="">Select your local bank</option>
                      <option>ABA Bank</option>
                      <option>ACLEDA Bank</option>
                      <option>Wing Bank</option>
                      <option>Canadia Bank</option>
                      <option>Sathapana Bank</option>
                    </select>
                    <span class="material-symbols-outlined absolute right-4 top-1/2 -translate-y-1/2 pointer-events-none text-[#72796e]">expand_more</span>
                  </div>
                </div>

                <div class="space-y-2">
                  <label class="text-sm font-bold text-[#42493e] ml-1">Account Holder Name</label>
                  <input class="w-full bg-[#e9e8e5] border-none rounded-lg py-4 px-4 focus:ring-2 focus:ring-[#154212]/20 text-[#1b1c1a] font-medium" placeholder="As it appears on your card" type="text" />
                </div>

                <div class="space-y-2">
                  <label class="text-sm font-bold text-[#42493e] ml-1">Account Number</label>
                  <input class="w-full bg-[#e9e8e5] border-none rounded-lg py-4 px-4 focus:ring-2 focus:ring-[#154212]/20 text-[#1b1c1a] font-medium" placeholder="000 000 000" type="text" />
                </div>

                <div class="pt-4">
                  <button class="w-full py-4 bg-gradient-to-br from-[#154212] to-[#2d5a27] text-white font-[Manrope,sans-serif] font-bold rounded-lg shadow-lg hover:shadow-xl transition-all active:scale-[0.99]" type="submit">
                    Link Account Securely
                  </button>
                  <p class="text-center mt-4 text-xs text-[#42493e] font-medium">By linking your account, you agree to our Terms of Financial Service.</p>
                </div>
              </form>
            </div>

            <div class="space-y-6 md:sticky md:top-28 md:justify-self-end md:w-full md:max-w-[300px] lg:max-w-[320px]">
              <div class="bg-[#f5f3f0] p-6 rounded-xl border-l-4 border-[#006e1c] shadow-sm">
                <div class="flex items-center gap-3 mb-3 text-[#006e1c]">
                  <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">lock</span>
                  <span class="font-[Manrope,sans-serif] font-bold text-sm uppercase tracking-wider">Secure Verification</span>
                </div>
                <p class="text-sm text-[#42493e] leading-relaxed">
                  Your bank details are protected by industry-leading 256-bit encryption. FarmLink Cambodia never stores your full banking credentials on our local servers.
                </p>
              </div>

              <div class="bg-[#efeeea] p-6 rounded-xl space-y-4">
                <h4 class="font-[Manrope,sans-serif] font-bold text-[#154212]">Why link your bank?</h4>
                <ul class="space-y-3">
                  <li class="flex items-start gap-3">
                    <span class="material-symbols-outlined text-[#006e1c] text-lg">bolt</span>
                    <span class="text-sm text-[#42493e]">Instant payments for faster deliveries.</span>
                  </li>
                  <li class="flex items-start gap-3">
                    <span class="material-symbols-outlined text-[#006e1c] text-lg">redeem</span>
                    <span class="text-sm text-[#42493e]">Exclusive "Loyal Tiller" reward points.</span>
                  </li>
                  <li class="flex items-start gap-3">
                    <span class="material-symbols-outlined text-[#006e1c] text-lg">receipt</span>
                    <span class="text-sm text-[#42493e]">Direct digital tax receipts for every buy.</span>
                  </li>
                </ul>
              </div>

              <div class="relative overflow-hidden rounded-xl h-48 group">
                <img class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-700" alt="Market produce" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDew-ujek9V9LwFWk8HsDAmZpxVhf_yIcUnc--XFelAMDyFhsFvTcT-s61jngjQ_HB23DpEklrRcBmZGVfMSa5cRFK261rIyU8e9bgscx58gZT-HKYPCxFeg9RV5hzflW3H1gAvrnbOMUXS_gZyymM3W9-QLpSpH7gnTM-wLVdcmCQhpLgy5652KVdpcJcjYmYBMqsRV3ujtgRsDlQiijWCyjijoys8_DSe9tyREZuJF9CEKqaYs703NsVJvWliAf0LnAD50GEznHY" />
                <div class="absolute inset-0 bg-gradient-to-t from-[#154212]/80 to-transparent flex items-end p-4">
                  <p class="text-white font-[Manrope,sans-serif] font-bold text-xs uppercase tracking-widest">Supporting Khmer Farmers</p>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>

    <footer class="w-full py-12 px-8 grid grid-cols-1 md:grid-cols-2 gap-8 border-t border-[#efeeea] bg-[#efeeea] mt-auto">
      <div class="space-y-6">
        <span class="text-lg font-black text-[#154212] font-[Manrope,sans-serif] uppercase tracking-tight">Farm Link</span>
        <p class="text-[#5a5b58] text-sm max-w-sm leading-relaxed">
          Empowering the soil-keepers of Cambodia through transparent commerce and direct-to-consumer sustainability.
        </p>
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

