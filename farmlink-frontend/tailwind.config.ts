import type { Config } from 'tailwindcss'

export default <Partial<Config>>{
	content: [
		'./app.vue',
		'./app/**/*.{vue,js,ts}',
		'./components/**/*.{vue,js,ts}',
		'./layouts/**/*.vue',
		'./pages/**/*.vue',
		'./plugins/**/*.{js,ts}',
		'./error.vue',
	],
	theme: {
		extend: {},
	},
}
