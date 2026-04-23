import type { Config } from 'tailwindcss'

export default {
    content: [
        '@/assets/css/main.css',
        './components/**/*.{vue,js,ts}',
        './layouts/**/*.vue',
        './pages/**/*.vue',
        './plugins/**/*.{js,ts}',
        './app.vue',
        './index.html", "./src/**/*.{vue,js}',
    ],
    theme: {
        extend: {},
    },
    plugins: [],
} satisfies Config