/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    borderWidth: {
      DEFAULT: '1px',
      '0': '0',
      '2': '2px',
      '3': '3px',
      '4': '4px',
      '6': '6px',
      '8': '8px',
      '12': '12px'
    },
    extend: {
      spacing: {
        '1/10': '9%',
      },
      height: {
        'header-weg': '9vh'
      },
      minHeight: {
        'header-weg': '5rem'
      }
    },
  },
  plugins: [],
}
