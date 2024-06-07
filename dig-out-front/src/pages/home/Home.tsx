import React from 'react'
import { BirthdateCarousel } from '../../components/carousel/birth/BirthdateCarousel.tsx';
import { Intro } from '../../components/intro/Intro.tsx';
import { DeathdateCarousel } from '../../components/carousel/death/DeathdateCarousel.tsx';
import { DeceasedSearch } from '../../components/deceased/deseasedSearch/DeseacedSearch.tsx';

export const Home = () => {
  return (
    <div className='home'>
      <Intro/>
      <BirthdateCarousel/>
      <DeathdateCarousel/>
      <DeceasedSearch/>
    </div>
  )
}
