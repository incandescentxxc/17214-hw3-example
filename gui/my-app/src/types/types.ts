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
	worker: WorkerData;
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