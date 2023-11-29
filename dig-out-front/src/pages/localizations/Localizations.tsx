import React from 'react';
import { LocalizationByIdForm } from '../../components/localizationByIdForm/LocalizationByIdForm.tsx';
import { AllLocalizations } from '../../components/allLocalizations/AllLocalizations.tsx';
import { NewLocalizationForm } from '../../components/newLocalizationForm/NewLocalizationForm.tsx';
import { RemoveLocalization } from '../../components/removeLocalization/RemoveLocalization.tsx';

export const Localizations = () => {

  return (
    <>
      <h1 className='title'> LOCALIZATIONS </h1>
      <hr/>
      <LocalizationByIdForm/>
      <hr/>
      <RemoveLocalization/>
      <hr/>
      <NewLocalizationForm/>
      <hr/>
      <AllLocalizations/>
    </>
  )
}
