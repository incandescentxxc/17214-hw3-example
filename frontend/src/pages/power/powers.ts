import ordinary from '@/assets/Default.png';
import athena from '@/assets/Athena.png';
import demeter from '@/assets/Demeter.png';
import pan from '@/assets/Pan.png';
import minotaur from '@/assets/Minotaur.png';
import apollo from '@/assets/Apollo.png';
import artemis from '@/assets/Artemis.png';
import atlas from '@/assets/Atlas.png';
import hephaestus from '@/assets/Hephaestus.png';
import hermes from '@/assets/Hermes.png';
import prometheus from '@/assets/Prometheus.png';

export default {
	powers: {
		Default: {
			name: 'Default',
			src: ordinary,
			description: 'You are just a normal player.',
		},
		Athena: {
			name: 'Athena',
			src: athena,
			symbol: 'Goddess of Wisdom',
			description:
				'Opponents Turn: If one of your Workers moved up on your last turn, opponent Workers cannot move up this turn.',
		},
		Demeter: {
			name: 'Demeter',
			src: demeter,
			symbol: 'Goddess of Harvest',
			description: 'Your Build: Your Worker may build one additional time, but not on the same space.',
		},
		Pan: {
			name: 'Pan',
			src: pan,
			symbol: 'God of the Wild',
			description: 'Win Condition: You also win if your Worker moves down two or more levels.',
		},
		Minotaur: {
			name: 'Minotaur',
			src: minotaur,
			symbol: 'Bull-headed Monster',
			description:
				'Your Move: Your Worker may move into an opponent Worker’s space, if their Worker can beforced one space straight backwards to an unoccupied space at any level.',
		},
		Apollo: {
			name: 'Apollo',
			src: apollo,
			symbol: 'God of Music',
			description:
				'Your Move: Your Worker may move into an opponent Worker’s space by forcing their Worker to the space yours just vacated.',
		},
		Artemis: {
			name: 'Artemis',
			src: artemis,
			symbol: 'Goddess of the Hunt',
			description: 'Your Move: Your Worker may move one additional time, but not back to its initial space.',
		},
		Atlas: {
			name: 'Atlas',
			src: atlas,
			symbol: 'Titan Shouldering the Heavens',
			description: 'Your Build: Your Worker may build a dome at any level.',
		},
		Hephaestus: {
			name: 'Hephaestus',
			src: hephaestus,
			symbol: 'God of Blacksmiths',
			description:
				'Your Build: Your Worker may build one additional block (not dome) on top of your first block.',
		},
		Hermes: {
			name: 'Hermes',
			src: hermes,
			symbol: 'God of Travel',
			description:
				'Your Turn: If your Workers do not move up or down, they may each move any number of times (even zero), and then either builds.',
		},
		Prometheus: {
			name: 'Prometheus',
			src: prometheus,
			symbol: 'Titan Benefactor of Mankind',
			description: 'If your Worker does not move up, it may build both before and after moving.',
		},
	},
};
