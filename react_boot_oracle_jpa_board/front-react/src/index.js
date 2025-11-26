import ReactDOM from 'react-dom/client';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // <React.StrictMode>     // 이게 있으면 2번씩 실행될 수 있음.
  <App />
  // </React.StrictMode>
);


