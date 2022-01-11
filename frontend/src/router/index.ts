exports.routes = [
	{
		path: '/',
		component: '@/layouts/index',
		routes: [
			{ path: '/', component: '@/pages/index' },
			{ path: '/power', component: '@/pages/power/index' },
			{ path: '/start', component: '@/pages/start/index' },
			{ path: '/santorini', component: '@/pages/santorini/index' },
		],
	},
];
