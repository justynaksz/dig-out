import React from 'react';

import { LocalizationByIdForm } from '../../components/localizations/localizationByIdForm/LocalizationByIdForm.tsx';
import { NewLocalizationForm } from '../../components/localizations/newLocalizationForm/NewLocalizationForm.tsx';
import { RemoveLocalization } from '../../components/localizations/removeLocalization/RemoveLocalization.tsx';
import { AllLocalizations } from '../../components/localizations/allLocalizations/AllLocalizations.tsx';
import  '../Pages.scss';


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
