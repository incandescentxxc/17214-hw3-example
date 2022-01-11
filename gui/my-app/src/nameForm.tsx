import React, { ChangeEvent, Component, FormEvent, SyntheticEvent } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';

type Props = {};

type State = {
	player1Name: string;
    player2Name: string;
};

export class NameForm extends Component<Props, State> {
	constructor(props: any) {
		super(props);
		this.state = { player1Name: 'player1', player2Name: 'player2' };
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(event: FormEvent<HTMLInputElement>, playerId: string) {
        if(playerId === 'player1') {
            this.setState({ player1Name: event.currentTarget.value});
        } else {
            this.setState({ player2Name: event.currentTarget.value});
        }
	}

	handleSubmit(event: SyntheticEvent) {
		console.log('hahah');
        
	}

	render() {
		return (
			<>
				<form onSubmit={this.handleSubmit}>
					<label>
						Player1 Name:
						<input type="text" value={this.state.player1Name} onChange={(e) => this.handleChange(e, 'player1')} />
					</label>
                    <label>
                        Player2 Name:
                        <input type="text" value={this.state.player2Name} onChange={(e) => this.handleChange(e, 'player2')} />
                    </label>
					<input type="submit" value="Submit" />
				</form>
				<Link to="/option">ss</Link>
			</>
		);
	}
}
