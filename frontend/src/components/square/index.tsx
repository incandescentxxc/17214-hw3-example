import { SquareProps } from '@/types/types';
import { Button, Popover } from 'antd';
import { useState } from 'react';
import styles from './index.less';

const Square = (props: SquareProps) => {
	const { gameId, id, status, tower, worker, canSelect, activePlayerId, getActivePlayerPower, dispatch } = props;
	let [showBuildChoices, setShowBuildChoices] = useState(false);
	const contentGenerator = () => {
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

	const onClick = (status: string, canSelect: boolean, getActivePlayerPower: string) => {
		if (!canSelect) {
			return;
		}
		if (status == 'BUILD' && getActivePlayerPower == 'Atlas') {
			setShowBuildChoices(true);
			return;
		}
		switch (status) {
			case 'PLACE':
				dispatch({
					type: 'santorini/placeWorker',
					payload: {
						gameId: gameId,
						positionX: Number.parseInt(id[0]),
						positionY: Number.parseInt(id[1]),
						playerId: activePlayerId,
					},
				});
				break;
			case 'SELECT':
				dispatch({
					type: 'santorini/selectWorker',
					payload: {
						gameId: gameId,
						positionX: Number.parseInt(id[0]),
						positionY: Number.parseInt(id[1]),
						playerId: activePlayerId,
						workerId: worker.workerId,
					},
				});
				break;
			case 'MOVE':
				dispatch({
					type: 'santorini/moveWorker',
					payload: {
						gameId: gameId,
						positionX: Number.parseInt(id[0]),
						positionY: Number.parseInt(id[1]),
						playerId: activePlayerId,
					},
				});
				break;
			case 'BUILD':
				dispatch({
					type: 'santorini/buildWorker',
					payload: {
						gameId: gameId,
						positionX: Number.parseInt(id[0]),
						positionY: Number.parseInt(id[1]),
						playerId: activePlayerId,
					},
				});
				break;
			case 'MOVE_OR_SKIP':
				dispatch({
					type: 'santorini/moveWorker',
					payload: {
						gameId: gameId,
						positionX: Number.parseInt(id[0]),
						positionY: Number.parseInt(id[1]),
						playerId: activePlayerId,
					},
				});
				break;
			case 'BUILD_OR_SKIP':
				dispatch({
					type: 'santorini/buildWorker',
					payload: {
						gameId: gameId,
						positionX: Number.parseInt(id[0]),
						positionY: Number.parseInt(id[1]),
						playerId: activePlayerId,
					},
				});
				break;
			default:
				break;
		}
		return;
	};

	const buildBlock = () => {
		setShowBuildChoices(false);
		dispatch({
			type: 'santorini/buildWorker',
			payload: {
				gameId: gameId,
				positionX: Number.parseInt(id[0]),
				positionY: Number.parseInt(id[1]),
				playerId: activePlayerId,
				buildDome: 0,
			},
		});
	};

	const buildDome = () => {
		setShowBuildChoices(false);
		dispatch({
			type: 'santorini/buildWorker',
			payload: {
				gameId: gameId,
				positionX: Number.parseInt(id[0]),
				positionY: Number.parseInt(id[1]),
				playerId: activePlayerId,
				buildDome: 1,
			},
		});
	};

	const canBuildBlock = () => {
		if (tower === null) {
			return true;
		}
		if (tower.hasDome) {
			return false;
		}
		if (tower.level < 3) {
			return true;
		}
		return false;
	};

	const content = (
		<div>
			{canBuildBlock() && <Button onClick={buildBlock}>Build a Block</Button>}
			<Button onClick={buildDome}>Build a Dome</Button>
		</div>
	);

	const getStyleClass = (canSelect: boolean, status: string) => {
		if (!canSelect) {
			return styles.square;
		}
		switch (status) {
			case 'SELECT':
				return styles.selectSquare;
			case 'PLACE':
				return styles.selectSquare;
			case 'BUILD':
				return styles.buildSquare;
			case 'MOVE':
				return styles.moveSquare;
			case 'MOVE_OR_SKIP':
				return styles.moveSquare;
			case 'BUILD_OR_SKIP':
				return styles.buildSquare;
			default:
				return styles.square;
		}
	};

	return (
		<Popover
			content={content}
			trigger="click"
			visible={showBuildChoices}
			onVisibleChange={() => onClick(status, canSelect, getActivePlayerPower)}
		>
			<button className={`${styles.square} ${getStyleClass(canSelect, status)}`}>
				{contentGenerator(tower, worker)}
			</button>
		</Popover>
	);
};

export default Square;
