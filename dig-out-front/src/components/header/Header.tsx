import React, { useEffect, useState } from 'react'
import { NavLink } from "react-router-dom";

export const Header = () => {
  
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    console.log(localStorage.getItem("token"))
    setIsAuthenticated(!localStorage.getItem("token"))
  })

  return (
  

    <header className="header">
    <nav className="nav">
      <li className='a'><NavLink className= 'link' to="/localizations">Localizations</NavLink></li>
      <li className='a'><NavLink className= 'link' to="/graves">Graves</NavLink></li>
      <li className='a'><NavLink className= 'link' to="/deceased">Deceased</NavLink></li>
      {!isAuthenticated ? 
      <>
        <li className='a'><NavLink className= 'link' to="/profile">Profile</NavLink></li>
        <li className='a'><NavLink className= 'link' to="/logout">Log Out</NavLink></li>
      </>
          :
      <>
        <li className='a'><NavLink className='link' to="/registration">Registration</NavLink></li>
        <li className='a'><NavLink className='link' to="/login">Login</NavLink></li>
      </>}
    </nav>
  </header>
  )
}
