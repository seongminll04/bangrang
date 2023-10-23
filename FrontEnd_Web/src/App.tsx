import React from 'react';
import { Route, Routes, useLocation } from "react-router-dom";

import './App.css';
import Home from './pages/home';
import Intro from './pages/intro';
import ManageHome from './pages/managehome';
import Navbar from './components/navbar';
import RequestManager from './pages/requestmanager';

function App() {
  const location = useLocation();
  return (
    <div className="App">
      <Navbar />      
      <Routes location={location}>
        <Route path="/" element={<Home />} />
        <Route path="/intro" element={<Intro />} />
        <Route path="/requestmanager" element={<RequestManager />} />
        <Route path="/manage" element={<ManageHome />} />
      </Routes>
    </div>
  );
}

export default App;
