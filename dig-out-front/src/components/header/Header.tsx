import React, { useEffect, useState } from 'react'
import { NavLink } from "react-router-dom";

export const Header = () => {
  
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    setIsAuthenticated(!localStorage.getItem("token"))
  })

  return (
    <header id="header" className="fixed-top d-flex align-items-cente">
      <div className="container-fluid container-xl d-flex align-items-center justify-content-lg-between">
  
        <h1 className="logo me-auto me-lg-0"><NavLink className= 'logo' to="/">dig-out</NavLink></h1>
  
        <nav id="navbar" className="navbar order-last order-lg-0">
          <ul>
            <li><NavLink className= 'nav-link scrollto active' to="/">Home</NavLink></li>
            <li><NavLink className= 'nav-link scrollto' to="/deceased">Deceased</NavLink></li>
          </ul>
          <i className="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <nav id="navbar" className="navbar order-last order-lg-0">
          <ul>
            <li className="dropdown"><span>Account</span><i className="bi bi-chevron-down"></i>
            {isAuthenticated ?
              <ul>
                <li><NavLink className= 'nav-link scrollto' to="/login">Log in</NavLink></li>
                <li><NavLink className= 'nav-link scrollto' to="/registration">Register</NavLink></li>
              </ul>
              :
              <ul>
                <li><NavLink className= 'nav-link scrollto' to="/profile">Profile</NavLink></li>
                <li><NavLink className= 'nav-link scrollto' to="/logout">Log out</NavLink></li>
              </ul>
            }
            </li> 
          </ul>
        </nav>
      </div>
    </header>
  )
}
