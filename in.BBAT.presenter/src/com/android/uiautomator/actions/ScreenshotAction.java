/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.uiautomator.actions;

import in.BBAT.abstrakt.presenter.device.model.AndroidDevice;
import in.BBAT.abstrakt.presenter.device.model.TestDeviceManager;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.android.uiautomator.UiAutomatorHelper;
import com.android.uiautomator.UiAutomatorHelper.UiAutomatorException;
import com.android.uiautomator.UiAutomatorHelper.UiAutomatorResult;
import com.android.uiautomator.UiAutomatorViewer;

public class ScreenshotAction extends Action {
    UiAutomatorViewer mViewer;

    public ScreenshotAction(UiAutomatorViewer viewer) {
        super("&Device Screenshot");
        mViewer = viewer;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return ImageHelper.loadImageDescriptorFromResource("icons/screenshot.png");
    }

    @Override
    public void run() {
       /* if (!DebugBridge.isInitialized()) {
            MessageDialog.openError(mViewer.getShell(),
                    "Error obtaining Device Screenshot",
                    "Unable to connect to adb. Check if adb is installed correctly.");
            return;
        }*/

        final AndroidDevice device = pickDevice();
        if (device == null) {
            return;
        }

        ProgressMonitorDialog dialog = new ProgressMonitorDialog(mViewer.getShell());
        try {
            dialog.run(true, false, new IRunnableWithProgress() {
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException,
                                                                        InterruptedException {
                    UiAutomatorResult result = null;
                    try {
                        result = UiAutomatorHelper.takeSnapshot(device, monitor);
                    } catch (UiAutomatorException e) {
                        monitor.done();
                        showError(e.getMessage(), e);
                        return;
                    }

                    mViewer.setModel(result.model, result.uiHierarchy, result.screenshot);
                    monitor.done();
                }
            });
        } catch (Exception e) {
            showError("Unexpected error while obtaining UI hierarchy", e);
        }
    }

    private void showError(final String msg, final Throwable t) {
      Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                Status s = new Status(IStatus.ERROR, "Screenshot", msg, t);
                ErrorDialog.openError(
                        mViewer.getShell(), "Error", "Error obtaining UI hierarchy", s);
            }
        });
    }

    private AndroidDevice pickDevice() {
        List<AndroidDevice> devices = TestDeviceManager.getInstance().getDevices();
        if (devices.size() == 0) {
            MessageDialog.openError(mViewer.getShell(),
                    "Error obtaining Device Screenshot",
                    "No Android devices were detected by adb.");
            return null;
        } else if (devices.size() == 1) {
            return devices.get(0);
        } else {
            DevicePickerDialog dlg = new DevicePickerDialog(mViewer.getShell(), devices);
            if (dlg.open() != Window.OK) {
                return null;
            }
            return dlg.getSelectedDevice();
        }
    }

    private static class DevicePickerDialog extends Dialog {
        private final List<AndroidDevice> mDevices;
        private final String[] mDeviceNames;
        private static int sSelectedDeviceIndex;

        public DevicePickerDialog(Shell parentShell, List<AndroidDevice> devices) {
            super(parentShell);

            mDevices = devices;
            mDeviceNames = new String[mDevices.size()];
            for (int i = 0; i < devices.size(); i++) {
                mDeviceNames[i] = devices.get(i).getName();
            }
        }

        @Override
        protected Control createDialogArea(Composite parentShell) {
            Composite parent = (Composite) super.createDialogArea(parentShell);
            Composite c = new Composite(parent, SWT.NONE);

            c.setLayout(new GridLayout(2, false));

            Label l = new Label(c, SWT.NONE);
            l.setText("Select device: ");

            final Combo combo = new Combo(c, SWT.BORDER | SWT.READ_ONLY);
            combo.setItems(mDeviceNames);
            int defaultSelection =
                    sSelectedDeviceIndex < mDevices.size() ? sSelectedDeviceIndex : 0;
            combo.select(defaultSelection);
            sSelectedDeviceIndex = defaultSelection;

            combo.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent arg0) {
                    sSelectedDeviceIndex = combo.getSelectionIndex();
                }
            });

            return parent;
        }

        public AndroidDevice getSelectedDevice() {
            return mDevices.get(sSelectedDeviceIndex);
        }
    }
}
