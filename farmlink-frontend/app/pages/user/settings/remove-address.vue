<script setup lang="ts">
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Delete Address Confirmation | FarmLink',
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
        <aside class="w-full lg:w-[280px] shrink-0">
          <div class="bg-white rounded-2xl border border-zinc-200 p-5 sm:p-6 shadow-sm">
            <div class="flex items-center gap-3 mb-5">
              <img
                src="https://lh3.googleusercontent.com/aida-public/AB6AXuCA83x8AHQV-oGz8CINH3SrXquf2gJKH9G9CVM3OVmDN3cB3DlMZ8OKkFiQ6GOy52l0KObZYfls2JxyvOI98GKD7NfFwfqx0DFo0t-2YFkuLovUIbiufm26ot4sXnGGxIM2I2HJDVGlcryJSFSWwtSvjU_kM5Ukd7XLzqRh0TtvXigMvcnSSB_3sieWsfz6wndum2oJtb1LLll8OD48U8SyMvtpcYou8NOZi9JGGjQyxIbbVWefdH5bFmPhCa9meAhjW2CO_fJBhigk"
                alt="User avatar"
                class="w-14 h-14 rounded-full object-cover"
              />
              <div>
                <h3 class="font-[Manrope,sans-serif] text-2xl leading-none font-extrabold text-zinc-900">Sophal Saman</h3>
                <p class="text-sm text-zinc-500 font-medium mt-1">Premium Member</p>
              </div>
            </div>

            <nav class="space-y-2">
              <NuxtLink to="/user/settings/profile" class="flex items-center gap-3 px-4 py-3 rounded-xl text-zinc-700 hover:bg-zinc-100 text-[15px] font-bold tracking-wide uppercase transition-colors">
                <span class="material-symbols-outlined text-[20px]">person</span>
                Profile Info
              </NuxtLink>

              <NuxtLink to="/user/settings/edit" class="flex items-center gap-3 px-4 py-3 rounded-xl text-zinc-700 hover:bg-zinc-100 text-[15px] font-bold tracking-wide uppercase transition-colors">
                <span class="material-symbols-outlined text-[20px]">edit</span>
                Edit Profile
              </NuxtLink>

              <a href="#" class="flex items-center gap-3 px-4 py-3 rounded-xl text-zinc-700 hover:bg-zinc-100 text-[15px] font-bold tracking-wide uppercase transition-colors">
                <span class="material-symbols-outlined text-[20px]">receipt_long</span>
                Purchase History
              </a>

              <NuxtLink to="/user/settings/address" class="flex items-center gap-3 bg-[#1f7a2e] text-white px-4 py-3 rounded-xl text-[15px] font-bold tracking-wide uppercase shadow-md">
                <span class="material-symbols-outlined text-[20px]">location_on</span>
                Addresses
              </NuxtLink>

              <NuxtLink to="/user/settings/payment" class="flex items-center gap-3 px-4 py-3 rounded-xl text-zinc-700 hover:bg-zinc-100 text-[15px] font-bold tracking-wide uppercase transition-colors">
                <span class="material-symbols-outlined text-[20px]">payments</span>
                Payment Methods
              </NuxtLink>

              <a href="#" class="flex items-center gap-3 px-4 py-3 rounded-xl text-zinc-700 hover:bg-zinc-100 text-[15px] font-bold tracking-wide uppercase transition-colors">
                <span class="material-symbols-outlined text-[20px]">chat</span>
                Chat
              </a>
            </nav>
          </div>
        </aside>

        <section class="flex-1 relative">
          <div class="opacity-35 blur-[2px] pointer-events-none select-none">
            <header class="mb-12">
              <span class="text-[#006e1c] font-bold text-[10px] tracking-[0.25em] uppercase mb-2 block">Shipping & Logistics</span>
              <h1 class="text-5xl font-black text-[#154212] font-[Manrope,sans-serif] tracking-tight">Saved Addresses</h1>
            </header>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
              <div class="bg-white rounded-xl p-8 border border-[#c2c9bb]/15">
                <div class="flex justify-between items-start mb-6">
                  <span class="bg-[#91f78e] text-[#00731e] px-4 py-1 rounded-full text-xs font-bold uppercase tracking-tighter">Primary Origin</span>
                  <div class="flex gap-2">
                    <span class="material-symbols-outlined text-[#72796e]">edit</span>
                    <span class="material-symbols-outlined text-[#ba1a1a]">delete</span>
                  </div>
                </div>
                <h3 class="text-xl font-bold text-[#154212] mb-2">Home Sanctuary</h3>
                <p class="text-[#42493e] leading-relaxed">No. 42, St. 310, Boeung Keng Kang I,<br />Phnom Penh, Cambodia</p>
              </div>

              <div class="bg-[#efeeea] rounded-xl p-8 border-2 border-[#ba1a1a]/20 ring-4 ring-[#ba1a1a]/5">
                <div class="flex justify-between items-start mb-6">
                  <span class="bg-[#e9e8e5] text-[#42493e] px-4 py-1 rounded-full text-xs font-bold uppercase tracking-tighter">Warehouse</span>
                  <div class="flex gap-2">
                    <span class="material-symbols-outlined text-[#72796e]">edit</span>
                    <span class="material-symbols-outlined text-[#ba1a1a]">delete</span>
                  </div>
                </div>
                <h3 class="text-xl font-bold text-[#154212] mb-2">Battambang Depot</h3>
                <p class="text-[#42493e] leading-relaxed">Highway 5, Sangkat Svay Pao,<br />Battambang City, Cambodia</p>
              </div>
            </div>
          </div>

          <div class="fixed inset-0 z-50 flex items-center justify-center bg-[#fbf9f6]/20 backdrop-blur-md p-4">
            <div class="bg-white w-full max-w-xl rounded-xl shadow-2xl p-10 border border-[#c2c9bb]/20">
              <div class="flex flex-col items-center text-center">
                <div class="w-16 h-16 bg-[#ffdad6] rounded-full flex items-center justify-center mb-6">
                  <span class="material-symbols-outlined text-[#ba1a1a] text-3xl" style="font-variation-settings: 'FILL' 1;">warning</span>
                </div>
                <span class="text-[10px] uppercase tracking-[0.2em] text-[#563000] font-bold mb-3 block">Security Confirmation</span>
                <h2 class="text-3xl font-black font-[Manrope,sans-serif] text-[#154212] mb-4 leading-tight">Are you sure you want to delete this address?</h2>
                <p class="text-[#42493e] mb-8 max-w-sm">This action will permanently remove <span class="font-bold text-[#154212]">Battambang Depot</span> from your digital ledger. This cannot be undone.</p>

                <div class="w-full bg-[#f5f3f0] rounded-lg p-6 mb-10 text-left border-l-4 border-[#ba1a1a]/50">
                  <p class="text-sm font-bold text-[#154212] uppercase tracking-widest mb-2">Address Details</p>
                  <p class="text-[#42493e] font-medium">Highway 5, Sangkat Svay Pao, Battambang City, Cambodia</p>
                </div>

                <div class="flex flex-col sm:flex-row gap-4 w-full">
                  <button class="flex-1 bg-[#ba1a1a] hover:bg-[#8f1010] text-white font-bold py-4 px-8 rounded-lg transition-all shadow-lg active:scale-95 flex items-center justify-center gap-2" type="button">
                    <span class="material-symbols-outlined text-xl">delete_forever</span>
                    Delete Address
                  </button>
                  <NuxtLink class="flex-1 border-2 border-[#c2c9bb]/20 hover:border-[#154212]/50 text-[#154212] font-bold py-4 px-8 rounded-lg transition-all active:scale-95" to="/user/settings/address">
                    Keep Address
                  </NuxtLink>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>

    <div class="fixed bottom-0 right-0 -z-10 w-1/3 h-1/2 opacity-10 pointer-events-none">
      <img alt="farmland background" class="object-cover w-full h-full grayscale" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBqRi0A_SI9CWPDWbKbN566XIqEcZ6laM5uurz_xf3yyW-790sf9Yz7qbMtpvNrC_BFA1FH14NhB5epbjhQzlIa_w4ygr9kG3aXItmyUBb4MJaPiMXef0iBHGliHFfRtqWiltVis5u7BGb1bjE-6AV1FhtboLEGr9s6xzmdmrVIuUaIMVsm0npwiiFxQMIWXYfVfdebPnKB-3sAvUjNv4hquPdkarC0nToxP_3G-TzJtk5lCOSS-86mDms7GPW3HTtkkz1BYgZcfzM" />
    </div>
  </div>
</template>