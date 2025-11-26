import AppContent from "./components/AppContent";
import Header from "./components/Header";
import AccountListPage from "./components/AccountListPage";
import './App.css';


function App() {
  return (
    <div className="App">
      <Header pageTitle='Fronted authenticated with JWT' />
      <div className='container-fluid'>
        <div className='row'>
          <div className='col'>
            {/* <AppContent /> */}
            <AccountListPage />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;