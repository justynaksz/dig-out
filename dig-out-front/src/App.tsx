import React from 'react';
import './App.scss';
import { Header } from './components/header/Header.tsx';
import { Routes, Route } from 'react-router-dom';
import { Home } from './pages/Home.tsx';
import { Localizations } from './pages/localizations/Localizations.tsx';
import { Graves } from './pages/graves/Graves.tsx';
import { DeceasedPage } from './pages/deceased/DeceasedPage.tsx';

function App() {

  return (
    <div className='App'>
      <Header/>
      <Routes>
        <Route path = '/' element = {<Home/>}/>
        <Route path = '/localizations' element = {<Localizations/>}/>
        <Route path = '/graves' element = {<Graves/>}/>
        <Route path = '/deceased' element = {<DeceasedPage/>}/>
    </Routes>
    </div>
  );
}

export default App;
