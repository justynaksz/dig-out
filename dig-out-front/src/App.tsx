import React from 'react';
import './App.scss';
import { Header } from './components/header/Header.tsx';
import { Routes, Route } from 'react-router-dom';
import { Home } from './pages/Home.tsx';
import { Localizations } from './pages/localizations/Localizations.tsx';
import { Graves } from './pages/graves/Graves.tsx';
import { DeceasedPage } from './pages/deceased/DeceasedPage.tsx';
import { RegistrationForm } from './pages/user/RegistrationForm.tsx';
import { LoginForm } from './pages/user/LoginForm.tsx';
import { NotLoggedIn } from './pages/user/NotLoggedIn.tsx';
import { UserById } from './components/users/userById/UserById.tsx';
import { LoggedOut } from './pages/user/LoggedOut.tsx';

function App() {

  return (
    <div className='App'>
      <Header/>
      <Routes>
        <Route path = '/' element = {<Home/>}/>
        <Route path = '/localizations' element = {<Localizations/>}/>
        <Route path = '/graves' element = {<Graves/>}/>
        <Route path = '/deceased' element = {<DeceasedPage/>}/>
        <Route path = '/registration' element = {<RegistrationForm/>}/>
        <Route path = '/login' element = {<LoginForm/>}/>
        <Route path = '/profile' element = {<UserById/>}/>
        <Route path = '/notloggedin' element = {<NotLoggedIn/>}/>
        <Route path = '/logout' element = {<LoggedOut/>}/>
    </Routes>
    </div>
  );
}

export default App;
