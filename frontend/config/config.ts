import { defineConfig } from 'umi';

const router = require('../src/router');
const routes = router.routes;

export default defineConfig({
	nodeModulesTransform: {
		type: 'none',
	},
	routes: routes,
	fastRefresh: {},
	dva: {
		immer: true,
		hmr: false,
	},
	proxy: {
		'/game': {
			target: 'http://localhost:8080',
			changeOrigin: true,
		},
	},
});
