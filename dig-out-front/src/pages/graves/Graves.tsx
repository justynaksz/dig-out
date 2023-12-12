import React from 'react';
import { GraveByIdForm } from '../../components/graves/graveByIdForm/GraveByIdForm.tsx';
import { RemoveGrave } from '../../components/graves/removeGrave/RemoveGrave.tsx';
import { NewGraveForm } from '../../components/graves/newGraveForm/NewGraveForm.tsx';
import { AllGraves } from '../../components/graves/allGraves/AllGraves.tsx';

export const Graves = () => {
  return (
    <>
      <h1 className='title'> GRAVES </h1>
      <hr/>
      <GraveByIdForm/>
      <hr/>
      <RemoveGrave/>
      <hr/>
      <NewGraveForm/>
      <hr/>
      <AllGraves/>
    </>
  )
}
