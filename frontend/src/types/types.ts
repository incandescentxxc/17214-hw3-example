export interface PlayerData {
	playerId: string;
	isActive: boolean;
	name: string;
	power: string;
	hasWon: boolean;
}

export interface TowerData {
	level: number;
	hasDome: boolean;
}

export interface WorkerData {
	workerId: number;
}

export interface GridData {
	id: string;
	tower: TowerData;
}

export interface GameData {
	id: string;
	gameStatus: string;
	player1: PlayerData;
	player2: PlayerData;
	board: {
		grids: GridData[][];
	};
	operableGridIds: string[];
}

export interface MainPageProps {
	gameData: GameData;
	dispatch: Function;
}
export interface SquareProps {
	gameId: string;
	id: string;
	status: string;
	tower: {
		level: number;
		hasDome: boolean;
	};
	worker: {
		workerId: number;
	};
	canSelect: boolean;
	activePlayerId: string;
	getActivePlayerPower: string;
	dispatch: Function;
}
