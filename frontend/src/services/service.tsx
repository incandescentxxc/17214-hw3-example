import request from 'umi-request';

export function startGame(vals) {
	return request('/game/start', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(vals),
	});
}

export function placeWorker(vals) {
	return request('game/placeWorker', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(vals),
	});
}

export function selectWorker(vals) {
	return request('game/select', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(vals),
	});
}

export function unSelectWorker(vals) {
	return request('game/unselect', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(vals),
	});
}

export function moveWorker(vals) {
	return request('game/move', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(vals),
	});
}

export function buildWorker(vals) {
	return request('game/build', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(vals),
	});
}

export function skip(vals) {
	return request('game/skip', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(vals),
	});
}
