import React, { FormEvent, SyntheticEvent, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

type Props = {};

const NameForm = (props: Props) => {
    
    // useState Hooks
    let [player1Name, setPlayer1Name] = useState('player1');
    let [player2Name, setPlayer2Name] = useState('player2');

    // useNavigate Hook, used for navigating to the other page
    let navigate = useNavigate();

    let handleChange = (event: FormEvent<HTMLInputElement>, playerId: string) => {
        if (playerId === 'player1') {
            setPlayer1Name(event.currentTarget.value);
		} else {
            setPlayer2Name(event.currentTarget.value);
		}
    }
    
    let handleSubmit = (event: SyntheticEvent) => {
        navigate('/game');
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label>
                    Player1 Name:
                    <input
                        type="text"
                        value={player1Name}
                        onChange={(e) => handleChange(e, 'player1')}
                    />
                </label>
                <label>
                    Player2 Name:
                    <input
                        type="text"
                        value={player2Name}
                        onChange={(e) => handleChange(e, 'player2')}
                    />
                </label>
                <input type="submit" value="Submit" />
            </form>
            <Link to="/game">ss</Link>
        </>
    );
}

export default NameForm;

