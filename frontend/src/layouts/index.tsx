import { Layout } from 'antd';
import { Link } from 'umi';
import styles from './index.less';

const GameHeader = (props: any) => {
	const { Header } = Layout;
	return (
		<Header className="header">
			<div className={styles.headerTitle}>
				<Link to="/">Santorini</Link>
			</div>
			{props.children}
		</Header>
	);
};

export default GameHeader;
