import React from 'react'
import { useState, useEffect } from 'react';
import api from '../../../api/axiosConfig.js'
import { DeceasedType } from '../../deceased/deceased/Deceased.tsx';
import { Carousel } from '../Carousel.tsx';

export const BirthdateCarousel = () => {

    const [birthdateDeceased, setBirthdateDeceased] = useState<DeceasedType[]>([] as DeceasedType[]);

    const getBirthdateDeceased = async () => {
        try {
            const response = await api.get("/deceased/birthdate");
            setBirthdateDeceased(response.data);
        } catch (error) {
            console.log(error)
        }
    };

    useEffect(() => {
        getBirthdateDeceased();
    }, [])

    return (
        <section id='birthdate' className='birthdate' data-aos="fade-up">
            <div className="section-title">
                <h2>Birth date anniversary</h2>
            </div>
            <Carousel anniversaryDeceased={birthdateDeceased}/>
        </section>
    )
}