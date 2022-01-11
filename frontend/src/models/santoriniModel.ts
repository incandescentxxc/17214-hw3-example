import {
	buildWorker,
	moveWorker,
	placeWorker,
	unSelectWorker,
	selectWorker,
	skip,
	startGame,
} from '@/services/service';

export default {
	namespace: 'santorini',

	state: {
		id: '',
		gameStatus: '',
		player1: null,
		player2: null,
		board: {
			grids: [[]],
		},
		winner: null,
		operableGridIds: [],
	},

	effects: {
		*createGame({ payload }, { call, put }) {
			const response = yield call(startGame, payload);
			yield put({
				type: 'getGameData',
				payload: response,
			});
		},

		*placeWorker({ payload }, { call, put }) {
			const response = yield call(placeWorker, payload);
			yield put({
				type: 'getGameData',
				payload: response,
			});
		},

		*selectWorker({ payload }, { call, put }) {
			const response = yield call(selectWorker, payload);
			yield put({
				type: 'getGameData',
				payload: response,
			});
		},

		*unSelectWorker({ payload }, { call, put }) {
			const response = yield call(unSelectWorker, payload);
			yield put({
				type: 'getGameData',
				payload: response,
			});
		},

		*moveWorker({ payload }, { call, put }) {
			const response = yield call(moveWorker, payload);
			yield put({
				type: 'getGameData',
				payload: response,
			});
		},

		*buildWorker({ payload }, { call, put }) {
			const response = yield call(buildWorker, payload);
			yield put({
				type: 'getGameData',
				payload: response,
			});
		},

		*skip({ payload }, { call, put }) {
			const response = yield call(skip, payload);
			yield put({
				type: 'getGameData',
				payload: response,
			});
		},
	},

	reducers: {
		getGameData(state, { payload }) {
			return {
				...state,
				...payload,
			};
		},
	},
};
