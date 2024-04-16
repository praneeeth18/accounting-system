
/*import { AbstractControl, ValidatorFn } from '@angular/forms';

export function dateNotInFuture(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const selectedDate = new Date(control.value);
    const currentDate = new Date();
 
    if (selectedDate > currentDate) {
      return { 'futureDate': true };
    }
    return null;
  };
}*/


import { AbstractControl, ValidatorFn } from '@angular/forms';

export function dateNotInFuture(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const selectedDate = new Date(control.value);
    const currentDate = new Date();
    const sevenDaysLater = new Date(currentDate);
    sevenDaysLater.setDate(currentDate.getDate() - 7);

    if (selectedDate > currentDate || selectedDate <= sevenDaysLater) {
      return { 'futureDate': true };
    }
    return null;
  };
}


