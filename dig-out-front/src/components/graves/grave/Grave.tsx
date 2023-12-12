import React from 'react'
import { LocalizationType } from '../../localizations/localization/Localization.tsx';

export type GraveType = {
  id: string;
  type: string;
  localization: LocalizationType;
  graveOwner: string;
}

export const Grave = ({
  id, 
  type,
  localization,
  graveOwner} : GraveType) => {
  return (
    <div>
      <div className='idLine'>Id: {id}</div>
      <table className='center'>
        <tbody>
        <tr className='itemLine'>
          <td>Type: </td><td>{type}</td>
        </tr>
        <tr className='itemLine'> 
          <td>Localization cemetery: </td><td>{localization?.cemetery}</td>
        </tr>
        <tr className='itemLine'> 
          <td>Localization quarter: </td><td>{localization?.quarter}</td>
        </tr>
        <tr className='itemLine'> 
          <td>Localization row: </td><td>{localization?.localizationRow}</td>
        </tr>
        <tr className='itemLine'> 
          <td>Localization column: </td><td>{localization?.localizationColumn}</td>
        </tr>
        <tr className='itemLine'>
          <td>Grave owner:</td><td>{graveOwner}</td>
        </tr>
        </tbody>
      </table>
    </div>
  )
}
