import styles from './index.less';
import { Row, Col, Form, Input, Button, Select, Divider, Card } from 'antd';
import { history } from '@/.umi/core/history';
import { startGame } from '@/services/service';
import defaultExport from '@/pages/power/powers';
import { connect } from 'umi';
import { useState } from 'react';

const StartPage = (props: any) => {
	const { dispatch, location } = props;
	let [power1, setPower1] = useState('');
	let [power2, setPower2] = useState('');

	const layout = {
		labelCol: { span: 10 },
		wrapperCol: { span: 16 },
	};

	const tailLayout = {
		wrapperCol: { offset: 8, span: 16 },
	};

	const [form] = Form.useForm();

	const onFinish = (values: any) => {
		let startPlayer = location.query.player === 'Player1' ? 0 : 1;
		let payload = {
			...values,
			power1: getPlayerName() === 'Player1' ? power1.toUpperCase() : power2.toUpperCase(),
			power2: getPlayerName() === 'Player1' ? power2.toUpperCase() : power1.toUpperCase(),
			startPlayer: startPlayer,
		};
		dispatch({
			type: 'santorini/createGame',
			payload: payload,
		});
		history.push('/santorini');
	};

	const getPlayerName = () => {
		if (location.query.player === 'Player1') {
			return 'Player2';
		}
		return 'Player1';
	};
	let { powers } = defaultExport;
	let powersToDisplay = [powers[location.query.power1], powers[location.query.power2]];
	console.log(powersToDisplay);
	const onClick = (power: string) => {
		if (power1 === '') {
			setPower1(power);
			if (power === powersToDisplay[0].name) {
				setPower2(powersToDisplay[1].name);
			} else {
				setPower2(powersToDisplay[0].name);
			}
		}
	};

	const FormArea = (
		<div className={styles.container}>
			<Form
				{...layout}
				form={form}
				name="control-hooks"
				onFinish={onFinish}
				initialValues={{ name1: 'Player1', name2: 'Player2' }}
			>
				<Form.Item name="name1" label="Player 1 Name">
					<Input />
				</Form.Item>

				<Divider />
				<Form.Item name="name2" label="Player 2 Name">
					<Input />
				</Form.Item>
				<Divider />

				<Form.Item {...tailLayout}>
					<Button type="primary" htmlType="submit">
						Start the Game!
					</Button>
				</Form.Item>
			</Form>
		</div>
	);
	return (
		<>
			<Row justify="center">
				<h1 className={styles.title}>{getPlayerName()}, please choose your power</h1>
			</Row>
			<Row justify="center">
				{power1 === '' &&
					powersToDisplay.map((power) => {
						if (power.name !== 'Default') {
							return (
								<Card
									className={styles.powerCard}
									hoverable
									cover={<img alt={power.name} src={power.src} className={styles.powerCardImg} />}
									onClick={() => onClick(power.name)}
								>
									<h2>{power.name}</h2>
									<h3>{power.symbol}</h3>
									{power.description}
								</Card>
							);
						}
					})}
				{power1 !== '' && (
					<h2>
						{location.query.player}: {power2}; {getPlayerName()}: {power1}
					</h2>
				)}
			</Row>
			{power1 !== '' && (
				<Row justify={'center'}>
					<Col span={6}></Col>
					<Col span={12} className={styles.middleCol}>
						<h1>Enter your names</h1>
						{FormArea}
					</Col>
					<Col span={6}></Col>
				</Row>
			)}
		</>
	);
};
const mapStateToProps = (props) => {
	const {} = props;
	return {};
};

export default connect(mapStateToProps)(StartPage);
