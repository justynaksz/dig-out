import React from 'react'
import { NavLink } from "react-router-dom";

export const Header = () => (
    <header className="header">
    <nav className="nav">
      <li className='a'><NavLink className= 'link' to="/localizations">Localizations</NavLink></li>
      <li className='a'><NavLink className= 'link' to="/graves">Graves</NavLink></li>
      <li className='a'><NavLink className= 'link' to="/deceased">Deceased</NavLink></li>
    </nav>
  </header>
)
