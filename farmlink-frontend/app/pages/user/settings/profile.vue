<script setup lang="ts">
import CommonAppSidebar from '../../../../components/common/AppSidebar.vue';
definePageMeta({
	layout: 'user',
});

useHead({
	title: 'Profile Overview | FarmLink Cambodia',
	link: [
		{
			rel: 'stylesheet',
			href: 'https://fonts.googleapis.com/css2?family=Manrope:wght@400;700;800&family=Inter:wght@400;500;600;700&display=swap',
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
				<CommonAppSidebar active="profile" />

				<div class="flex-1 min-w-0">
					<header class="mb-8 sm:mb-10">
						<p class="text-[#006e1c] font-extrabold text-[10px] uppercase tracking-[0.2em] mb-3">Account Management</p>
						<h1 class="text-4xl sm:text-5xl lg:text-6xl font-extrabold text-[#154212] font-[Manrope,sans-serif] tracking-tight">Profile Overview</h1>
					</header>

					<div class="grid grid-cols-1 xl:grid-cols-12 gap-8">
						<section class="xl:col-span-8 space-y-8">
							<div class="bg-white p-5 sm:p-8 rounded-2xl border border-zinc-100 shadow-sm flex flex-col md:flex-row gap-8 items-center md:items-start">
								<div class="relative shrink-0">
									<div class="w-32 h-32 sm:w-36 sm:h-36 rounded-2xl overflow-hidden border-4 border-[#fbf9f6] shadow-lg">
										<img
											src="https://lh3.googleusercontent.com/aida-public/AB6AXuDfJPIP46m2uRjQOOpIBiadImhVQdU9Dbb-4izWzGm6WuwKu1lpUvd5QSJO1aA1CkPRf263rAyyc34Abx0mBy9XwXscHVFeJy13CGLy2GgJ9V64Khs93UXU0flXRlbqe1FUR5GyNaxVjgUwhrsSqHMnZyxbqGYEa9KWhBQATBWJi1I1ELik_TaeYp4H3rs5YpW7YJNuCGUXxeQ7qOIyceTpk1MzAKlv4kY9TM8SsCAHJAta-uEkMC7Y67kv2S1kxMiaXudOuWMHfsI"
											alt="Profile portrait"
											class="w-full h-full object-cover"
										/>
									</div>
									<span class="absolute -bottom-2 -right-2 bg-[#006e1c] text-white text-[10px] font-black px-3 py-1 rounded-full">PREMIUM</span>
								</div>

								<div class="flex-1 grid grid-cols-1 sm:grid-cols-2 gap-6 sm:gap-y-7">
									<div>
										<p class="text-[10px] font-extrabold text-zinc-500 uppercase tracking-widest mb-1">Full Name</p>
										<p class="text-xl font-bold text-[#154212] font-[Manrope,sans-serif]">Sophal Saman</p>
									</div>
									<div>
										<p class="text-[10px] font-extrabold text-zinc-500 uppercase tracking-widest mb-1">Email Address</p>
										<p class="text-xl font-bold text-[#154212] font-[Manrope,sans-serif] break-all">soman.sophal@farm.kh</p>
									</div>
									<div>
										<p class="text-[10px] font-extrabold text-zinc-500 uppercase tracking-widest mb-1">Phone Number</p>
										<p class="text-xl font-bold text-[#154212] font-[Manrope,sans-serif]">+855 12 345 678</p>
									</div>
									<div>
										<p class="text-[10px] font-extrabold text-zinc-500 uppercase tracking-widest mb-1">Member Since</p>
										<p class="text-xl font-bold text-[#154212] font-[Manrope,sans-serif]">October 2023</p>
									</div>
								</div>
							</div>

							<section class="space-y-4">
								<h3 class="text-2xl font-extrabold font-[Manrope,sans-serif] text-[#154212]">Quick Statistics</h3>
								<div class="grid grid-cols-1 sm:grid-cols-3 gap-5">
									<article class="bg-white p-5 rounded-2xl border border-zinc-100">
										<p class="text-[10px] font-extrabold text-zinc-500 uppercase tracking-widest">Total Orders</p>
										<div class="flex items-end gap-2 mt-2">
											<p class="text-3xl font-black text-[#154212] font-[Manrope,sans-serif]">580</p>
											<span class="material-symbols-outlined text-[#006e1c] mb-1">shopping_bag</span>
										</div>
									</article>

									<article class="bg-white p-5 rounded-2xl border border-zinc-100">
										<p class="text-[10px] font-extrabold text-zinc-500 uppercase tracking-widest">Saved Products</p>
										<div class="flex items-end gap-2 mt-2">
											<p class="text-3xl font-black text-[#154212] font-[Manrope,sans-serif]">40</p>
											<span class="material-symbols-outlined text-[#006e1c] mb-1">bookmark</span>
										</div>
									</article>

									<article class="bg-white p-5 rounded-2xl border border-zinc-100">
										<p class="text-[10px] font-extrabold text-zinc-500 uppercase tracking-widest">Active Shipments</p>
										<div class="flex items-end gap-2 mt-2">
											<p class="text-3xl font-black text-zinc-400 font-[Manrope,sans-serif]">0</p>
											<span class="material-symbols-outlined text-zinc-400 mb-1">local_shipping</span>
										</div>
									</article>
								</div>
							</section>
						</section>

						<aside class="xl:col-span-4 space-y-6">
							<div class="bg-white p-6 rounded-2xl border border-zinc-100 shadow-sm">
								<h3 class="text-xl font-extrabold font-[Manrope,sans-serif] text-[#154212] mb-6">Notifications</h3>
								<div class="space-y-6">
									<div class="flex items-center justify-between">
										<div>
											<p class="text-sm font-bold text-[#154212]">Order Updates</p>
											<p class="text-[11px] text-zinc-500">Fresh deliveries tracking</p>
										</div>
										<div class="w-12 h-6 rounded-full bg-[#006e1c] relative">
											<div class="w-4 h-4 rounded-full bg-white absolute right-1 top-1" />
										</div>
									</div>

									<div class="flex items-center justify-between">
										<div>
											<p class="text-sm font-bold text-[#154212]">Price Alerts</p>
											<p class="text-[11px] text-zinc-500">Seasonal drops alerts</p>
										</div>
										<div class="w-12 h-6 rounded-full bg-[#006e1c] relative">
											<div class="w-4 h-4 rounded-full bg-white absolute right-1 top-1" />
										</div>
									</div>

									<div class="flex items-center justify-between opacity-60">
										<div>
											<p class="text-sm font-bold text-[#154212]">Market News</p>
											<p class="text-[11px] text-zinc-500">Weekly farmer insights</p>
										</div>
										<div class="w-12 h-6 rounded-full bg-zinc-200 relative">
											<div class="w-4 h-4 rounded-full bg-white absolute left-1 top-1" />
										</div>
									</div>
								</div>
							</div>

							<div class="bg-[#ffdcbe] p-6 rounded-2xl border border-[#f0c99f] shadow-[0_2px_0_0_rgba(0,0,0,1)]">
								<p class="text-[10px] font-extrabold text-[#693c00] uppercase tracking-widest mb-2">Reward Balance</p>
								<h4 class="text-4xl font-black text-[#2c1600] font-[Manrope,sans-serif] tracking-tight mb-5">4,250 <span class="text-sm font-bold uppercase">Points</span></h4>
								<button class="w-full py-3 rounded-xl bg-[#2c1600] text-white text-xs font-black uppercase tracking-widest">Redeem Rewards</button>
							</div>
						</aside>
					</div>
				</div>
			</div>
		</main>

		<footer class="w-full bg-[#fbf9f6] border-t border-zinc-200">
			<div class="max-w-[1440px] mx-auto px-6 py-10 flex flex-col md:flex-row gap-8 items-center justify-between">
				<div class="flex items-center gap-2">
					<img
						src="https://lh3.googleusercontent.com/aida-public/AB6AXuDxOVR_zyChQjJtpsz7uv6I95KZvXXMjdfhytppn0wii18iFOb85phtM6Me-Wq3F8rDH7KgRDh9ogSDlko0dxBiPcN3qVrhoLFXTG5GuYaVt05jNPcxWW12g7R9SnCr59Li2JIk2040vUdxOGHqHRlCeTc0WFN5koj8Y1cyRBd25JUuv7eamJG-EMr7Z4T4c3Hp9NtjqSvBoyma4cWZJxxQSKSZRKvtn56kwAmf27B7nViVqPc5vC2557SJdMeVhgzwu25O1U1P1pQ"
						alt="FarmLink logo"
						class="h-8"
					/>
					<div>
						<p class="text-xs font-black text-[#154212] uppercase">Farm Link</p>
						<p class="text-[10px] text-zinc-500">FarmLink Cambodia</p>
					</div>
				</div>

				<div class="text-center text-[11px] text-zinc-500 font-semibold uppercase tracking-[0.15em]">
					<p>Terms of Service | Privacy Policy | Shipping Info</p>
					<p class="mt-2">Returns | Support | Store Locator</p>
				</div>

				<div class="text-[10px] text-zinc-500 uppercase tracking-widest text-center md:text-right">
					<p>© 2024 FarmLink Cambodia</p>
					<p class="mt-1 text-zinc-400">Harvesting Trust, Delivering Nature.</p>
				</div>
			</div>
		</footer>
	</div>
</template>

<style scoped>
.material-symbols-outlined {
	font-variation-settings: 'FILL' 0, 'wght' 500, 'GRAD' 0, 'opsz' 24;
}
</style>

