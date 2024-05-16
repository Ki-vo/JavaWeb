import {fileURLToPath, URL} from 'node:url'

import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default ({mode, command}) => {
    const env = loadEnv(mode, process.cwd())
    const BASE_URL = env.VITE_BASE_URL
    const BASE_API = env.VITE_BASE_API

    return defineConfig({
        base: BASE_URL,
        plugins: [
            vue(),
            // ...
            AutoImport({
                resolvers: [ElementPlusResolver()],
            }),
            Components({
                resolvers: [ElementPlusResolver()],
            }),
        ],
        resolve: {
            alias: {
                '@': fileURLToPath(new URL('./src', import.meta.url))
            }
        }
        // ,
        // server: {
        //     proxy: {
        //         '/api': {
        //             target: BASE_API,
        //             changeOrigin: true,
        //             rewrite: (path) => path.replace(/^\/api/, '')
        //         }
        //     }
        // }
    })
}


