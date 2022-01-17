import React, { useContext, useEffect, useState } from 'react';
import GameContext from './gameContext';
import './styles/santorini.css';
import { GameData, GridData, TowerData, WorkerData } from './types/types';

type santProps = {};

const Santorini = (props: santProps) => {
	let gameInitData: GameData = {
		board: {
			grids: [],
		},
		gameStatus: '',
		id: '',
		operableGridIds: [],
		player1: {
			playerId: '',
			isActive: false,
			name: '',
			power: '',
			hasWon: false,
		},
		player2: {
			playerId: '',
			isActive: false,
			name: '',
			power: '',
			hasWon: false,
		},
	};

	let [gameData, setGameData] = useState(gameInitData);

	const { gameId } = useContext(GameContext);

	useEffect(() => {
		fetch('/check/' + gameId, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
			},
		})
			.then((response) => {
				return response.json();
			})
			.then((res) => {
				setGameData(res);
				console.log('update', gameData);
			})
			.catch((err) => {
				console.log('frontend init the game failed', err);
			});
	}, []);

	const checkSelect = (id: string, operableGridIds: string[]) => {
		return operableGridIds.includes(id);
	};

	const contentGenerator = (tower: TowerData, worker: WorkerData) => {
		let content = '';
		if (tower) {
			for (let i = 0; i < tower.level; i++) {
				content += '[';
			}
		}
		if (worker) {
			if (worker.workerId == 0) {
				content += '1-1';
			} else if (worker.workerId == 1) {
				content += '1-2';
			} else if (worker.workerId == 2) {
				content += '2-1';
			} else if (worker.workerId == 3) {
				content += '2-2';
			}
		} else if (tower?.hasDome) {
			content += 'O';
		}
		if (tower) {
			for (let i = 0; i < tower.level; i++) {
				content += ']';
			}
		}
		return content;
	};

	const getStyleClass = (canSelect: boolean, status: string) => {
		let base = 'square';
		if (!canSelect) {
			return base;
		}
		switch (status) {
			case 'SELECT':
				return base + ' selectSquare';
			case 'PLACE':
				return base + ' selectSquare';
			case 'BUILD':
				return base + ' buildSquare';
			case 'MOVE':
				return base + ' moveSquare';
			case 'MOVE_OR_SKIP':
				return base + ' moveSquare';
			case 'BUILD_OR_SKIP':
				return base + ' buildSquare';
			default:
				return base;
		}
	};

	const getActivePlayerId = (gameData: GameData) => {
		const { player1, player2 } = gameData;
		if (player1.isActive) {
			return player1.playerId;
		} else {
			return player2.playerId;
		}
	};

	const onClick = (gridData: GridData, gameData: GameData, canSelect: boolean) => {
		const { gameStatus } = gameData;
		const { worker, id } = gridData;
		let activePlayerId = getActivePlayerId(gameData);
		if (!canSelect) {
			return;
		}
		let apiType = '';
		let body = {};
		switch (gameStatus) {
			case 'PLACE':
				apiType = 'placeWorker';
				body = {
					gameId: gameId,
					positionX: Number.parseInt(id[0]),
					positionY: Number.parseInt(id[1]),
					playerId: activePlayerId,
				};
				break;
			case 'SELECT':
				apiType = 'select';
				body = {
					gameId: gameId,
					positionX: Number.parseInt(id[0]),
					positionY: Number.parseInt(id[1]),
					playerId: activePlayerId,
					workerId: worker.workerId,
				};
				break;
			case 'MOVE':
				apiType = 'move';
				body = {
					gameId: gameId,
					positionX: Number.parseInt(id[0]),
					positionY: Number.parseInt(id[1]),
					playerId: activePlayerId,
				};
				break;
			case 'BUILD':
				apiType = 'build';
				body = {
					gameId: gameId,
					positionX: Number.parseInt(id[0]),
					positionY: Number.parseInt(id[1]),
					playerId: activePlayerId,
				};
				break;
			case 'MOVE_OR_SKIP':
				apiType = 'move';
				body = {
					gameId: gameId,
					positionX: Number.parseInt(id[0]),
					positionY: Number.parseInt(id[1]),
					playerId: activePlayerId,
				};
				break;
			case 'BUILD_OR_SKIP':
				apiType = 'build';
				body = {
					gameId: gameId,
					positionX: Number.parseInt(id[0]),
					positionY: Number.parseInt(id[1]),
					playerId: activePlayerId,
				};
				break;
			default:
				break;
		}
		fetch(apiType, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(body),
		})
			.then((response) => {
				return response.json();
			})
			.then((res) => {
				setGameData(res);
			})
			.catch((err) => {
				console.log('frontend init the game failed', err);
			});

		return;
	};

	const buttonGenerator = (gridData: GridData, gameData: GameData) => {
		const { tower, worker, id } = gridData;
		let content = contentGenerator(tower, worker);
		let canSelect = checkSelect(id, gameData.operableGridIds);
		let styleClass = getStyleClass(canSelect, gameData.gameStatus);
		return (
			<button className={styleClass} key={id} onClick={() => onClick(gridData, gameData, canSelect)}>
				{content}
			</button>
		);
	};

	return (
		<div className="board">
			Your Game should be shown here
			{gameData.board.grids.map((row, id) => {
				return (
					<div key={id}>
						{row.map((val: GridData) => {
							return buttonGenerator(val, gameData);
						})}
					</div>
				);
			})}
		</div>
	);
};

export default Santorini;
