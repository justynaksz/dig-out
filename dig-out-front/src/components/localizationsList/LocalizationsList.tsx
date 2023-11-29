import React from 'react';
import { LocalizationType, Localization } from '../localization/Localization.tsx';

type Props = {
    localizations: LocalizationType[];
};

export const LocalizationsList = ({localizations} : Props) => {
  return ( 
    <div>
    <ul className='list'>
        {localizations.map((localization) => (
            <li key={localization.id}>
                <Localization {... localization}/>
            </li>
        ))}
    </ul>
    </div>
  )
}