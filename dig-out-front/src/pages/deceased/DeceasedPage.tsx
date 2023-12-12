import React from 'react'
import { AllDeceased } from '../../components/deceased/allDeceased/AllDeceased.tsx'
import { DeceasedByIdForm } from '../../components/deceased/deceasedByIdForm/DeceasedByIdForm.tsx'
import { RemoveDeceased } from '../../components/deceased/removeDeceased/RemoveDeceased.tsx'
import { NewDeceasedForm } from '../../components/deceased/newDeceasedForm/NewDeceasedForm.tsx'


export const DeceasedPage = () => {
  return (
    <>
    <h1 className='title'> DECEASED </h1>
    <hr/>
    <DeceasedByIdForm/>
    <hr/>
    <RemoveDeceased/>
    <hr/>
    <NewDeceasedForm/>
    <hr/>
    <AllDeceased/>
    </>
  )
}
