import React from 'react'
import { useState } from 'react';
import Slider from "react-slick";
import noPhoto from "../../assets/no-profile-picture.png";
import { FaArrowRight, FaArrowLeft } from "react-icons/fa";
import { DeceasedType } from '../deceased/deceased/Deceased.tsx';

type Props = {
    anniversaryDeceased: DeceasedType[];
};

export const Carousel = ({anniversaryDeceased}: Props) => {

    const NextArrow = ({ onClick }) => {
        return (
            <div className='arrow next' onClick={onClick}>
                <FaArrowRight/>
            </div>
        )
    }

    const PrevArrow = ({ onClick }) => {
        return (
            <div className='arrow prev' onClick={onClick}>
                <FaArrowLeft/>
            </div>
        )
    }

    const [imageIndex, setImageIndex] = useState(0);

    const settings = {
        infinite: true,
        lazyLoad: true,
        speed: 300,
        slidesToShow: 5,
        centerMode: true,
        centerPadding: 0,
        nextArrow: <NextArrow />,
        prevArrow: <PrevArrow />,
        beforeChange: (current, next) => setImageIndex(next)
    }

    return (
        <>
        <div> 
            <Slider {...settings}>
                {anniversaryDeceased?.map((deceased, idx) => (
                    <div key={deceased.id} className={idx === imageIndex ? "slide activeSlide" : "slide"}>
                        <div className='carousel-box'>
                            <img className='carousel-photo' src={noPhoto} alt="img"/>
                            <p className='carousel-names'>{deceased.firstName} {deceased.lastName}</p>
                            <p className='carousel-dates'>{deceased.birthDate}</p>
                            <p className='carousel-dates '>{deceased.deathDate}</p>
                        </div>
                    </div>
                ))}
            </Slider>
        </div>
        </>
    )
}

