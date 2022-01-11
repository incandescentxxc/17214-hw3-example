import styles from './index.less';
import { Row, Col, Button, Card, notification } from 'antd';
import { history } from '@/.umi/core/history';
import { connect } from 'umi';
import logo from '@/assets/bg-1.png';
import defaultExport from './powers';
import { useState } from 'react';

const PowerPage = (props) => {
	const { location } = props;
	const onClick = (powerName: string) => {
		if (power1 === '') {
			setPower1(powerName);
		} else if (power2 === '' && powerName !== power1) {
			setPower2(powerName);
		} else {
			notification.open({
				message: 'Only two powers can be chosen',
			});
		}
	};
	const onButtonClick = () => {
		if (power1 === '' || power2 === '') {
			notification.open({
				message: 'You need to select 2 powers',
			});
		} else {
			history.push({
				pathname: '/start',
				query: {
					player: location.query.player,
					power1: power1,
					power2: power2,
				},
			});
		}
	};
	let [power1, setPower1] = useState('');
	let [power2, setPower2] = useState('');
	let { powers } = defaultExport;
	let powersToDisplay = Object.values(powers);
	return (
		<>
			<Row justify="center">
				<h1 className={styles.title}>{location.query.player} chooses two kinds of power please</h1>
			</Row>
			<Row justify={'center'}>
				{powersToDisplay.map((power, id) => {
					console.log(id);
					if (power.name !== 'Default' && id <= 5) {
						return (
							<Col className={styles.col} span={4}>
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
							</Col>
						);
					}
				})}
			</Row>
			<Row justify={'center'}>
				{powersToDisplay.map((power, id) => {
					console.log(id);
					if (power.name !== 'Default' && id > 5) {
						return (
							<Col className={styles.col} span={4}>
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
							</Col>
						);
					}
				})}
			</Row>
			<Row justify="center">
				<h1>Power1: {power1}</h1>
			</Row>
			<Row justify="center">
				<h1>Power2: {power2}</h1>
			</Row>
			<Row justify="center">
				<Button className={styles.centerButton} type="primary" onClick={() => onButtonClick()}>
					Next
				</Button>
			</Row>
		</>
	);
};

const mapStateToProps = (props) => {
	const { santorini, loading } = props;
	return {};
};

export default connect(mapStateToProps)(PowerPage);
