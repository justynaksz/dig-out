import React from 'react'
import { NavLink } from "react-router-dom";

export const Intro = () => {
  return (
    <section id="hero" className="d-flex align-items-center">
    <div className="container position-relative text-center text-lg-start" data-aos="zoom-in" data-aos-delay="100">
      <div className="row">
        <div className="col-lg-8">
          <h1>welcome to <span>dig-out</span></h1>
          <h2>we've been waiting for you!</h2>

          <div className="btns">
            <NavLink className= 'btn-find animated fadeInUp scrollto' to="/deceased">Find your loved ones</NavLink>
          </div>
        </div>
      </div>
    </div>
  </section>
  )
}