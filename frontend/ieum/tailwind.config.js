/** @type {import('tailwindcss').Config} */
import DaisyUIPlugin from 'daisyui/plugin'

export default {
  prefix: 'tw-',
  content: [
    // Or if using `src` directory:
    './src/**/*.{js,ts,jsx,tsx}'
  ],
  theme: {
    extend: {}
  },
  plugins: [DaisyUIPlugin]
}
