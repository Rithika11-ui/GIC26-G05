<script setup lang="ts">
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Edit Profile - Remove Photo | FarmLink Cambodia',
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
  <div class="min-h-screen bg-[#fbf9f6] text-zinc-900 font-[Inter,sans-serif] overflow-hidden">
    <nav class="fixed top-0 z-50 w-full bg-[#f5f1e6] border-b border-zinc-200/70">
      <div class="max-w-[1440px] mx-auto px-4 sm:px-6 py-3 flex items-center justify-between gap-4">
        <div class="flex items-center gap-3">
          <img
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuDxOVR_zyChQjJtpsz7uv6I95KZvXXMjdfhytppn0wii18iFOb85phtM6Me-Wq3F8rDH7KgRDh9ogSDlko0dxBiPcN3qVrhoLFXTG5GuYaVt05jNPcxWW12g7R9SnCr59Li2JIk2040vUdxOGHqHRlCeTc0WFN5koj8Y1cyRBd25JUuv7eamJG-EMr7Z4T4c3Hp9NtjqSvBoyma4cWZJxxQSKSZRKvtn56kwAmf27B7nViVqPc5vC2557SJdMeVhgzwu25O1U1P1pQ"
            alt="FarmLink logo"
            class="h-10"
          />
          <div class="hidden sm:block">
            <p class="text-sm font-black text-[#154212] leading-tight tracking-tight uppercase font-[Manrope,sans-serif]">Farm Link</p>
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
          <button class="hidden md:inline-flex text-zinc-800"><span class="material-symbols-outlined">bookmark</span></button>
          <button class="hidden md:inline-flex text-zinc-800"><span class="material-symbols-outlined">shopping_cart</span></button>
          <button class="bg-[#e9c46a] px-4 sm:px-5 py-2 rounded-full border border-black text-[11px] font-extrabold uppercase tracking-wider shadow-[2px_2px_0_0_rgba(0,0,0,1)]">Shop Now</button>
          <button class="bg-[#e9c46a] p-1.5 rounded-full border border-black shadow-[1px_1px_0_0_rgba(0,0,0,1)]"><span class="material-symbols-outlined text-black">person</span></button>
        </div>
      </div>
    </nav>

    <div class="pt-24 flex min-h-[calc(100vh-96px)]">
      <aside class="w-72 bg-stone-100 p-6 border-r border-transparent">
        <div class="mb-8 px-2">
          <h2 class="text-lg font-bold text-emerald-900 font-[Manrope,sans-serif]">Profile Settings</h2>
          <p class="text-xs text-stone-500 font-medium">Manage your agrarian identity</p>
        </div>

        <nav class="flex flex-col gap-1">
          <a class="flex items-center gap-3 px-4 py-3 bg-white text-emerald-900 rounded-xl shadow-sm font-semibold" href="#">
            <span class="material-symbols-outlined text-[20px]">person</span>
            <span class="text-sm">Personal Info</span>
          </a>
          <a class="flex items-center gap-3 px-4 py-3 text-stone-500 hover:bg-stone-200/50 rounded-xl font-semibold" href="#">
            <span class="material-symbols-outlined text-[20px]">lock</span>
            <span class="text-sm">Security</span>
          </a>
          <a class="flex items-center gap-3 px-4 py-3 text-stone-500 hover:bg-stone-200/50 rounded-xl font-semibold" href="#">
            <span class="material-symbols-outlined text-[20px]">notifications</span>
            <span class="text-sm">Notifications</span>
          </a>
          <a class="flex items-center gap-3 px-4 py-3 text-stone-500 hover:bg-stone-200/50 rounded-xl font-semibold" href="#">
            <span class="material-symbols-outlined text-[20px]">photo_library</span>
            <span class="text-sm">Photo Gallery</span>
          </a>
          <a class="flex items-center gap-3 px-4 py-3 text-stone-500 hover:bg-stone-200/50 rounded-xl font-semibold" href="#">
            <span class="material-symbols-outlined text-[20px]">link</span>
            <span class="text-sm">Linked Accounts</span>
          </a>
        </nav>

        <div class="mt-8">
          <NuxtLink
            to="/user/settings/edit"
            class="block w-full text-center py-3 px-4 text-xs font-bold tracking-wider uppercase text-emerald-900 border border-emerald-900/20 rounded-xl hover:bg-emerald-900/5 transition-colors"
          >
            View Public Profile
          </NuxtLink>
        </div>
      </aside>

      <main class="flex-1 p-10 overflow-y-auto bg-stone-50/50 relative">
        <div class="absolute inset-0 bg-stone-900/10 z-10" />

        <div class="max-w-4xl mx-auto space-y-10 relative z-0">
          <header>
            <h1 class="text-4xl font-extrabold text-[#154212] font-[Manrope,sans-serif] mb-2">Personal Info</h1>
            <p class="text-zinc-500 font-medium">Update your photo and personal details here.</p>
          </header>

          <section class="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">
            <div class="md:col-span-1">
              <h3 class="font-[Manrope,sans-serif] text-lg font-bold text-zinc-900 mb-2">Your Photo</h3>
              <p class="text-sm text-zinc-500 leading-relaxed">This will be displayed on your profile and next to your activities.</p>
            </div>
            <div class="md:col-span-2 flex items-center gap-8 bg-[#efeeea] rounded-2xl p-8 border border-zinc-200/50">
              <img
                class="w-28 h-28 rounded-full object-cover border-4 border-white shadow-lg"
                alt="Profile image"
                src="https://lh3.googleusercontent.com/aida-public/AB6AXuAujS53ZwSWwbtYjoA-HvqMd_4sdPsSnNU8490o9hichROUdMwqdwfbZgvaPmYTBFi2lHt0_JOvKyBOrEaYwJ24phMW5FL-Qq0iNPz5W3KBZPTiRtrrlFNibRt-3A3qnaEXnWQoFuRMNufNyeF5y_Ds1iQTQeTged_yiYlU3UwFjQ5P6pV0acYFu3FWXbTYHaHwzcm3lz75sFGPwy-Jt96rsgcxo0fRIXUVap0GUNLDhn0TSXn2oMW-U6m-oxw7EPb345v-Vrcjl44"
              />
              <div class="flex flex-col gap-3">
                <NuxtLink to="/user/settings/photo" class="px-5 py-2.5 bg-[#154212] text-white rounded-lg font-bold text-sm text-center">
                  Change Photo
                </NuxtLink>
                <button class="px-5 py-2.5 border border-[#ba1a1a]/20 text-[#ba1a1a] rounded-lg font-bold text-sm">
                  Remove Photo
                </button>
              </div>
            </div>
          </section>
        </div>
      </main>
    </div>

    <div class="fixed inset-0 z-50 flex items-center justify-center bg-black/30 backdrop-blur-sm p-4">
      <div class="w-full max-w-md bg-white p-8 rounded-xl shadow-2xl border border-zinc-200/30">
        <div class="flex flex-col items-center text-center">
          <div class="w-16 h-16 rounded-full bg-[#ffdad6] flex items-center justify-center mb-6">
            <span class="material-symbols-outlined text-[#ba1a1a] text-3xl">delete_forever</span>
          </div>

          <h3 class="font-[Manrope,sans-serif] text-xl font-bold text-zinc-900 mb-2">Are you sure you want to remove your profile photo?</h3>
          <p class="text-zinc-500 text-sm mb-8 leading-relaxed">This action cannot be undone. You will revert to a default placeholder avatar.</p>

          <div class="flex gap-3 w-full">
            <NuxtLink
              to="/user/settings/edit"
              class="flex-1 px-6 py-3 rounded-lg font-semibold text-zinc-600 bg-[#e9e8e5] hover:bg-[#e3e2df] transition-colors text-center"
            >
              Cancel
            </NuxtLink>
            <button class="flex-1 px-6 py-3 rounded-lg font-semibold text-white bg-[#ba1a1a] hover:opacity-90 transition-opacity">
              Remove
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 500, 'GRAD' 0, 'opsz' 24;
}
</style>
