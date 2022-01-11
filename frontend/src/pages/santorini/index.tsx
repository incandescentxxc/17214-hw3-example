import styles from './index.less';
import { Row, Col, Form, Input, Button, Select, Divider, Card } from 'antd';
import { history } from '@/.umi/core/history';
import { connect } from 'umi';
import { startGame } from '@/services/service';
import Square from '@/components/square';
import { useEffect, useState } from 'react';
import { GameData, GridData, MainPageProps, PlayerData } from '@/types/types';
import defaultExport from '@/pages/power/powers';

const getPrompt = (player: PlayerData | undefined, status: string) => {
	let content = '';
	switch (status) {
		case 'PLACE':
			content = 'Place your worker';
			break;
		case 'SELECT':
			content = 'Select your worker';
			break;
		case 'MOVE_OR_SKIP':
			content = 'Move your worker or skip to build';
			break;
		case 'MOVE':
			content = 'Move your worker';
			break;
		case 'BUILD':
			content = 'Choose a square to build';
			break;
		case 'BUILD_OR_SKIP':
			content = 'Choose a square to build or skip your round';
			break;
		default:
			content = '';
	}

	if (player?.isActive) {
		return content;
	}

	return '';
};

const checkSelect = (id: string, operableGridIds: string[]) => {
	return operableGridIds.includes(id);
};

const MainPage = (props: MainPageProps) => {
	const { gameData, dispatch } = props;
	console.log('gameData', gameData, props);
	const { gameStatus, player1, player2, operableGridIds } = gameData;

	const getWinner = () => {
		if (player1?.hasWon) {
			return player1;
		} else if (player2?.hasWon) {
			return player2;
		}
		return null;
	};

	const getRound = () => {
		if (player1?.hasWon) {
			return player1.name + 'Won!';
		}
		if (player2?.hasWon) {
			return player2.name + 'Won!';
		}
		if (player1?.isActive) {
			return player1?.name + ': ' + getPrompt(player1, gameStatus);
		} else {
			return player2?.name + ': ' + getPrompt(player2, gameStatus);
		}
	};

	const getActivePlayerId = () => {
		if (player1.isActive) {
			return player1.playerId;
		} else {
			return player2.playerId;
		}
	};

	const getActivePlayerPower = () => {
		if (player1.isActive) {
			return player1.power;
		} else {
			return player2.power;
		}
	};

	const skipRound = () => {
		dispatch({
			type: 'santorini/skip',
			payload: {
				playerId: getActivePlayerId(),
				gameId: gameData.id,
				positionX: 0,
				positionY: 0,
			},
		});
	};

	const cancelSelect = () => {
		dispatch({
			type: 'santorini/unSelectWorker',
			payload: {
				playerId: getActivePlayerId(),
				gameId: gameData.id,
				positionX: 0,
				positionY: 0,
			},
		});
	};

	const onRestart = () => {
		history.push('/');
	};
	let { powers } = defaultExport;
	return (
		<>
			<Row justify="center">
				<h1 className={styles.title}>{getRound()}</h1>
			</Row>
			<Row justify={'center'}>
				<Col span={6}>
					{!getWinner() && (
						<Card
							className={styles.playerCard}
							hoverable
							cover={
								<img
									alt="example"
									src={player1 && powers[player1.power].src}
									className={styles.playerCardImg}
								/>
							}
						>
							<h2>
								{player1?.name} ({player1?.power})
							</h2>
							{player1 && powers[player1.power].description}
						</Card>
					)}
				</Col>
				<Col span={12} className={styles.board}>
					{getWinner() && (
						<div className={styles.winnerPannel}>
							<Card
								className={styles.playerCard}
								hoverable
								cover={
									<img
										alt="example"
										src={getWinner() && powers[getWinner().power].src}
										className={styles.playerCardImg}
									/>
								}
							>
								<h2>
									{getWinner()?.name} ({getWinner()?.power})
								</h2>
							</Card>
							<Button className={styles.restartButton} onClick={onRestart}>
								Play again!
							</Button>
						</div>
					)}
					{!getWinner() &&
						gameData?.board.grids.map((row, id) => {
							return (
								<div className={styles.boardRow} key={id}>
									{row.map((val: GridData) => {
										return (
											<Square
												dispatch={dispatch}
												key={val.id}
												gameId={gameData.id}
												status={gameData?.gameStatus}
												canSelect={checkSelect(val.id, operableGridIds)}
												activePlayerId={getActivePlayerId()}
												getActivePlayerPower={getActivePlayerPower()}
												{...val}
											/>
										);
									})}
								</div>
							);
						})}
					{!getWinner() && (gameStatus === 'BUILD_OR_SKIP' || gameStatus === 'MOVE_OR_SKIP') && (
						<Button className={styles.skipBtn} onClick={skipRound}>
							Skip This Round
						</Button>
					)}
					{!getWinner() && gameStatus === 'MOVE' && (
						<Button className={styles.skipBtn} onClick={cancelSelect}>
							Cancel This Selection
						</Button>
					)}
				</Col>
				<Col span={6}>
					{!getWinner() && (
						<Card
							className={styles.playerCard}
							hoverable
							cover={
								<img
									alt="example"
									src={player2 && powers[player2.power].src}
									className={styles.playerCardImg}
								/>
							}
						>
							<h2>
								{player2?.name}: ({player2?.power})
							</h2>
							{player2 && powers[player2.power].description}
						</Card>
					)}
				</Col>
			</Row>
		</>
	);
};

const mapStateToProps = (props) => {
	const { santorini, loading } = props;
	return {
		gameData: santorini,
	};
};

export default connect(mapStateToProps)(MainPage);
