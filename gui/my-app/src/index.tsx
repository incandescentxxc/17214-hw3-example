import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from "react-router-dom";

import './index.css';
import { NameForm } from './nameForm';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <NameForm />
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

