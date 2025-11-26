import React from 'react';

const Buttons = (props) => {
    return (
        <div className="row">
            <div className="col-md-12 text-center" style={{ marginTop: '30px' }}>
                <button className="btn btn-primary" style={{ margin: '10px' }} onClick={props.login}>
                    Login
                </button>
                <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.logout}>
                    LogOut
                </button>
            </div>
        </div>
    );
};

export default Buttons;