import React from 'react'
import { useState, useEffect } from 'react';
import api from '../../../api/axiosConfig.js'
import { DeceasedType } from '../../deceased/deceased/Deceased.tsx';
import { Carousel } from '../Carousel.tsx';

export const DeathdateCarousel = () => {

    const [deathdateDeceased, setDeathdateDeceased] = useState<DeceasedType[]>([] as DeceasedType[]);

    const getDeathdateDeceased = async () => {
        try {
            const response = await api.get("/deceased/deathdate");
            setDeathdateDeceased(response.data);
        } catch (error) {
            console.log(error)
        }
    };

    useEffect(() => {
        getDeathdateDeceased();
    }, [])

    return (
        <div data-aos="fade-up">
            <div className="section-title">
                <h2>Death date anniversary</h2>
            </div>
            <Carousel anniversaryDeceased={deathdateDeceased}/>
        </div>
    )
}