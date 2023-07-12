/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'conteudo.imguol.com.br'
      },
      {
        protocol: 'https',
        hostname: 'www.arquitetosdolanche.com.br'
      },
      {
        protocol: 'https',
        hostname: 'google.com'
      },
      {
        protocol: 'https',
        hostname: 'google.com.br'
      }
    ]
  }
}

module.exports = nextConfig
