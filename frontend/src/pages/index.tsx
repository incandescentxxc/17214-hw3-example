import styles from './index.less';
import { Row, Col, Button } from 'antd';
import { history } from '@/.umi/core/history';
import { connect } from 'umi';
import logo from '@/assets/bg-1.png';

const IndexPage = (props) => {
	const onClick = (player: string) => {
		history.push({
			pathname: '/power',
			query: {
				player: player,
			},
		});
	};
	return (
		<>
			<Row>
				<h2 className={styles.title}>Welcome to Santorini</h2>
			</Row>
			<Row justify={'center'}>
				<Col span={6}></Col>
				<Col span={12} className={styles.middleCol}>
					<div>
						<img src={logo}></img>
					</div>
					<h2>Who to Start?</h2>
					<div className={styles.buttonArea}>
						<Button className={styles.centerButton} type="primary" onClick={() => onClick('Player1')}>
							Player 1
						</Button>
						<Button className={styles.centerButton} type="primary" onClick={() => onClick('Player2')}>
							Player 2
						</Button>
					</div>
				</Col>
				<Col span={6}></Col>
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

export default connect(mapStateToProps)(IndexPage);
