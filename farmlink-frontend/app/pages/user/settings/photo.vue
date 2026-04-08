<script setup lang="ts">
definePageMeta({
  layout: 'user',
});

useHead({
  title: 'Edit Profile - Change Photo | FarmLink Cambodia',
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
            Back to Edit Profile
          </NuxtLink>
        </div>
      </aside>

      <main class="flex-1 p-10 overflow-y-auto bg-stone-50/50 relative">
        <div class="absolute inset-0 bg-stone-900/10 z-10" />

        <div class="max-w-4xl mx-auto space-y-10 relative z-0">
          <header>
            <span class="text-[10px] font-bold tracking-[0.2em] text-[#006e1c] uppercase">Account Management</span>
            <h1 class="text-5xl font-extrabold text-[#154212] font-[Manrope,sans-serif] mt-2 tracking-tight">Personal Details</h1>
          </header>

          <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div class="md:col-span-1">
              <div class="aspect-square rounded-2xl bg-[#e9e8e5] overflow-hidden">
                <img
                  class="w-full h-full object-cover"
                  alt="Profile image"
                  src="https://lh3.googleusercontent.com/aida-public/AB6AXuAa3VshPy0gbuZ6FQYP5J1UCTwCrG2jmdBoSptCyfqW0HXb8T2q8dd2ayAQCIMyjZ7mENPrgPAgKKkp2FnsRMp8bvXdKDsHbhjM89pTZBwB5ZGhO5n0ySn3H5_-cMxN4ppdGTqVM06Y_i4UUXg91UytDpMAfaAm3HVeDjWZRMfc4sE68VaJHlvbwC-9CPeeJxBLWEJPhTuDK6F13Zv6IBEdoWwLGmQ7s3ZnV66xR1fSit-HmGCmWtmlnnVXLEtkE6nom5ZTLS1J6qk"
                />
              </div>
            </div>
            <div class="md:col-span-2 space-y-8">
              <div class="grid grid-cols-2 gap-6">
                <div class="space-y-2">
                  <label class="text-xs font-bold text-zinc-500 ml-1">First Name</label>
                  <div class="h-12 w-full rounded-lg bg-[#efeeea] px-4 flex items-center text-zinc-500">Sophal</div>
                </div>
                <div class="space-y-2">
                  <label class="text-xs font-bold text-zinc-500 ml-1">Last Name</label>
                  <div class="h-12 w-full rounded-lg bg-[#efeeea] px-4 flex items-center text-zinc-500">Saman</div>
                </div>
              </div>
              <div class="space-y-2">
                <label class="text-xs font-bold text-zinc-500 ml-1">Bio</label>
                <div class="h-32 w-full rounded-lg bg-[#efeeea] p-4 text-zinc-500">Passionate about sustainable farming practices and local produce networks.</div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>

    <div class="fixed inset-0 z-50 flex items-center justify-center p-4 bg-[#154212]/20 backdrop-blur-sm">
      <div class="w-full max-w-lg bg-white rounded-[2rem] shadow-2xl overflow-hidden border border-white/20">
        <div class="p-8 space-y-6">
          <div class="flex justify-between items-center">
            <h2 class="text-2xl font-extrabold text-[#154212] font-[Manrope,sans-serif]">Upload Photo</h2>
            <NuxtLink to="/user/settings/edit" class="w-10 h-10 flex items-center justify-center rounded-full hover:bg-stone-100 transition-colors">
              <span class="material-symbols-outlined text-stone-400">close</span>
            </NuxtLink>
          </div>

          <div class="group relative flex flex-col items-center justify-center border-2 border-dashed border-zinc-300 rounded-3xl p-12 bg-[#f5f3f0]/70 hover:bg-[#f5f3f0] transition-all cursor-pointer">
            <div class="w-20 h-20 rounded-full bg-[#91f78e] flex items-center justify-center mb-4">
              <span class="material-symbols-outlined text-[#00731e] text-4xl">cloud_upload</span>
            </div>
            <p class="text-zinc-800 font-semibold">Drag and drop your image here</p>
            <p class="text-xs text-zinc-500 mt-2">Supports JPG, PNG (Max 5MB)</p>
            <div class="mt-6">
              <span class="px-6 py-2 bg-white text-[#154212] text-xs font-bold border border-zinc-300 rounded-full shadow-sm">Browse Files</span>
            </div>
          </div>

          <div class="flex gap-4 pt-2">
            <NuxtLink to="/user/settings/edit" class="flex-1 py-4 text-sm font-bold text-stone-600 hover:text-[#154212] transition-colors text-center">
              Cancel
            </NuxtLink>
            <button class="flex-1 py-4 bg-gradient-to-br from-[#154212] to-[#2d5a27] text-white text-sm font-bold rounded-xl shadow-lg shadow-[#154212]/20 active:scale-[0.98] transition-all">
              Upload
            </button>
          </div>
        </div>

        <div class="bg-[#efeeea] px-8 py-4 flex items-center gap-3">
          <span class="material-symbols-outlined text-[#006e1c] text-sm">lightbulb</span>
          <p class="text-[11px] text-zinc-600 font-medium">Tip: Use a bright, clear photo of yourself to build trust with fellow farmers.</p>
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
